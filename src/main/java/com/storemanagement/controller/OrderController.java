package com.storemanagement.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.storemanagement.dto.OrderDTO;
import com.storemanagement.dto.OrderRequestDTO;
import com.storemanagement.repo.OrderRepository;
import com.storemanagement.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/order")
public class OrderController {
	private OrderService orderService;
	private OrderRepository orderRepository;
	private Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	public OrderController(OrderService orderService, OrderRepository orderRepository) {
		this.orderService = orderService;
		this.orderRepository=orderRepository;
	}

	@PostMapping
	public OrderDTO createOrder(@RequestBody @Valid OrderRequestDTO orderDto) {
		return orderService.createOrder(orderDto);
	}
	
	@PutMapping
	public OrderDTO updateOrder(@RequestBody @Valid OrderRequestDTO orderDto) {
			try {
				if (orderDto.getId() == null) throw new Exception("Order id not provided!");
				return orderService.updateOrder(orderDto);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}

	@GetMapping("/{id}")
	public OrderDTO getOrder(@PathVariable Long id) {
		return orderService.getOrder(id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
	
	@GetMapping("/all/{ids}")
	public List<OrderDTO> getOrdersByIds(@PathVariable List<Long> ids){

		return orderRepository.findAllById(ids)
				.stream()				
                .map(orderEntity -> mapper.map(orderEntity, OrderDTO.class))
                .collect(Collectors.toList());

		
	}
	
	

}
