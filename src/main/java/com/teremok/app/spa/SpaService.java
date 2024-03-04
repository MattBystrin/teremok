package com.teremok.app.spa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.teremok.app.user.User;
import com.teremok.app.user.Role;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpaService {

	private final SpaRepository spaRepository;
	private final SpaRecordRepository spaRecordRepository;
	private final SpaScheduler sched;

	private Iterable<SpaRecordDTO> mapToDTO(Iterable<SpaRecord> recs) {
		Iterable<SpaRecordDTO> dtos = StreamSupport.stream(recs.spliterator(), false)
			.map(rec -> SpaRecordDTO.fromRecord(rec))
			.collect(Collectors.toList());
		return dtos;
	}

	public Iterable<SpaType> getTypes() {
		return spaRepository.findAll();
	}

	public Iterable<SpaRecordDTO> getRecords() {
		return mapToDTO(spaRecordRepository.findAll());
	}

	public Iterable<SpaRecordDTO> getRecordsSelf(User user) {
		if (user.getRole() == Role.SPA)
			return mapToDTO(spaRecordRepository.findByEmpl(user));

		return mapToDTO(spaRecordRepository.findByGuest(user));
	}

	public Iterable<SpaType> getCompatible(Long specie) {
		return spaRepository.findCompatible(specie);
	}

	public Iterable<SpaType> getAvailable(Long specie, LocalDate date, LocalTime time) {
		return spaRepository.findCompatible(specie);
	}

	@Transactional
	public void bookSpa(User user, Long spa_id, LocalDate date, LocalTime time) throws Exception {
		User empl = sched.getNext();

		if (!validateTime(time))
			throw new Exception("Invalid time range");

		SpaType type = spaRepository.findById(spa_id).get();

		SpaRecord spaRecord = SpaRecord.builder()
			.date(date)
			.time(time)
			.empl(empl)
			.guest(user)
			.type(type)
			.build();

		spaRecordRepository.save(spaRecord);
	}

	private Boolean validateTime(LocalTime time) {
		if (time.isAfter(LocalTime.parse("10:00")) && 
		    time.isBefore(LocalTime.parse("19:00"))) {
			return true;
		}

		return false;
	}
}
