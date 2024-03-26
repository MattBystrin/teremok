package com.teremok.app.cooking;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.teremok.app.user.Role;
import com.teremok.app.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CookService {

	private final DishRepository dishRepository;
	private final CookScheduler sched;
	private final CookRepository cookRepository;
	private final OrderRepository orderRepository;

	private Iterable<CookTaskDTO> mapToDTO(Iterable<CookTask> tasks) {
		Iterable<CookTaskDTO> dtos = StreamSupport.stream(tasks.spliterator(), false)
			.map(task -> CookTaskDTO.fromTask(task))
			.collect(Collectors.toList());
		return dtos;
	}

	public Iterable<Dish> getMenu(User user) {
		Long specie = user.getSpecie().getId();
		return dishRepository.findCompatible(specie);
	}

	public void order(User user, OrderDTO order) {
		User empl = sched.getNext();	
		LocalDate date = LocalDate.now();

		CookTask task = CookTask.builder()
			.date(date)
			.guest(user)
			.employee(empl)
			.build();

		task = cookRepository.save(task);

		for (OrderDTO.Position pos : order.getPositions()) {
			Dish dish = dishRepository.findById(pos.getDish()).get();
			Order orderEntity = Order.builder()
				.dish(dish)
				.quanitiy(pos.getQuantity())
				.task(task)
				.build();

			orderRepository.save(orderEntity);
		}
	}

	public Iterable<CookTaskDTO> getOrders(User user) {
		if (user.getRole() == Role.COOK)
			return mapToDTO(cookRepository.findByEmployee(user));

		return mapToDTO(cookRepository.findByGuest(user));
	}

	public Iterable<CookTaskDTO> getAllOrders() {
		return mapToDTO(cookRepository.findAll());
	}

	public void markDone(Long id) {
		CookTask task = cookRepository.findById(id).get();
		task.setReady(true);
		cookRepository.save(task);
	}
}
