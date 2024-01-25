package com.teremok.app.cleaning;

import java.time.LocalDate;

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

	public Iterable<CleaningTask> getQueue() {
		return cleaningRepository.findAll();
	}

	public Iterable<CleaningTask> getEmployeeQueue(Long id) {
		return cleaningRepository.findByUser(id);
	}

	public void saveReport(ReportDTO report) {
		CleaningTask task = cleaningRepository.findById(report.getTask()).get();
		String comment = report.getComment();

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

	public void addTask(Long room_id) {
		Room room = roomService.getRoom(room_id);
		User user = sched.assignCleaning();
		CleaningTask task = CleaningTask.builder()
			.room(room)
			.user(user)
			.date(LocalDate.now())
			.build();
		cleaningRepository.save(task);
	}
};
