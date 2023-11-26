package com.teremok.app.cleaning;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CleanService {
	
	// @Autowired
	// private CleanRepository clean_repo;
	//
	// @Autowired
	// private FurnitureRepository furniture_repo;
	//
	// @Autowired
	// private CleanReportRepository clean_report_repo;
	//
	// @Autowired
	// private RoomsRepository rooms_repo;
	//
	// @Autowired
	// private EmployeesRepository employee_repo;
	//
	// public Iterable<CleanTaskModel> getCleanQueue() {
	// 	Iterable<CleanTaskEntity> entites = clean_repo.findAll();
	// 	List<CleanTaskModel> list = new ArrayList<>();
	//
	// 	for (CleanTaskEntity it: entites) {
	// 		list.add(CleanTaskModel.toModel(it));
	// 	}
	//
	// 	return list;
	// }
	//
	// public void saveReport(CleanReportModel report) {
	// 	System.out.println(report.getRoom());
	// 	System.out.println(report.getComment());
	// 	
	// 	List<CleanDiff> diffs = report.getDiff();
	// 	for (CleanDiff diff : diffs) {
	// 		FurnitureEntity entity = furniture_repo.findById(diff.getId()).get();
	// 		entity.setState(diff.getState());
	// 		furniture_repo.save(entity);
	// 	}
	//
	// 	CleanReportEntity report_entity = new CleanReportEntity();
	// 	report_entity.setComment(report.getComment());
	// 	report_entity.setRoom(report.getRoom());
	// 	
	// 	clean_report_repo.save(report_entity);
	//
	// 	CleanTaskEntity task_entity = clean_repo.findPending(report.getRoom());
	// 	task_entity.setReady(true);
	//
	// 	clean_repo.save(task_entity);
	// }
	//
	// @Autowired
	// private NotificationService notify_service;
	// public void queueCleanTask(CleanTaskModel task) {
	// 	CleanTaskEntity entity = new CleanTaskEntity();
	// 	if (task.getDate() != null) {
	// 		entity.setDate(task.getDate());
	// 	} else {
	// 		entity.setDate(LocalDate.now());
	// 	}
	//
	// 	entity.setReady(false);
	// 	entity.setRoom(rooms_repo.findById(task.getRoom()).get());
	// 	entity.setEmployee(employee_repo.findById(task.getEmployee()).get());
	//
	// 	clean_repo.save(entity);
	//
	// 	notify_service.sendNotification(task.getEmployee(), "New cleaning task");
	// };
};
