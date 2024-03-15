package com.teremok.app.cleaning;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.teremok.app.hostel.furniture.FurnitureService;
import com.teremok.app.hostel.rooms.RoomService;
import com.teremok.app.user.User;
import com.teremok.app.hostel.rooms.Room;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CleaningService {

	private final FurnitureService furnitureService;
	private final RoomService roomService;

	private final CleaningRepository cleaningRepository;
	private final CleaningReportRepository cleaningReportRepository;
	private final DiffRepository diffRepository;

	private final Scheduler sched;

	private Iterable<CleaningTaskDTO> mapToDTO(Iterable<CleaningTask> tasks) {
		Iterable<CleaningTaskDTO> dtos = StreamSupport.stream(tasks.spliterator(), false)
			.map(task -> CleaningTaskDTO.fromTask(task))
			.collect(Collectors.toList());
		return dtos;
	}

	public Iterable<CleaningTaskDTO> getQueue() {
		return mapToDTO(cleaningRepository.findAll());
	}

	public Iterable<CleaningTaskDTO> getEmployeeQueue(Long uid) {
		return mapToDTO(cleaningRepository.findByUser(uid));
	}

	public void saveReport(ReportDTO report) throws Exception {
		CleaningTask task = cleaningRepository.findById(report.getTask()).get();
		String comment = report.getComment();

		if (report.getDiffs().isEmpty())
			throw new Exception("Diffs empty");

		CleaningReport clrep = CleaningReport.builder()
			.task(task)
			.comment(comment)
			.build();

		clrep = cleaningReportRepository.save(clrep);

		for (ReportDTO.ReportDiff diff: report.getDiffs()) {
			furnitureService.updateState(diff.getId(), diff.getDiff());
			Diff db_diff = Diff.builder()
				.diff(diff.getDiff())
				.furniture(furnitureService.getById(diff.getId()))
				.report(clrep)
				.build();
			diffRepository.save(db_diff);
		}

		task.setReady(true);
		cleaningRepository.save(task);
	}

	public void addTask(NewTaskDTO task) {
		Room room = roomService.getRoom(task.getRoom());
		User user = sched.assignCleaning();
		LocalDate date = task.getDate() != null ? task.getDate()
							: LocalDate.now();

		CleaningTask newTask = CleaningTask.builder()
			.room(room)
			.user(user)
			.date(date)
			.build();

		cleaningRepository.save(newTask);
	}
};
