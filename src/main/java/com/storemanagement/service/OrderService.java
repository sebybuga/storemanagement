package com.storemanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.storemanagement.dto.OrderDTO;
import com.storemanagement.dto.OrderPatchQuantityDTO;
import com.storemanagement.entity.OrderEntity;
import com.storemanagement.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
	//@Value("${usermanagement.user.getAllByIds}")
	//private String usermanagementGetAllByIdsUrl;
	private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	private OrderRepository orderRepository;
	//private UserService userService;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;

	}

	public OrderDTO createOrder(OrderDTO orderDTO) {

		OrderEntity orderEntity = mapper.map(orderDTO, OrderEntity.class);
		// System.out.println("projectEntity este:" + projectEntity.getName() + " " +
		OrderEntity savedOrder = orderRepository.save(orderEntity);

		OrderDTO savedOrderDTO = mapper.map(savedOrder, OrderDTO.class);

		//savedOrderDTO.setUserList(fetchUserList(savedOrderDTO.getUserList()));

		return savedOrderDTO;

	}

	public OrderDTO getOrder(Long id) {

		OrderDTO orderDTO=null;

		Optional<OrderEntity> orderEntity = orderRepository.findById(id);
		if (orderEntity.isPresent()) {
			orderDTO = mapper.map(orderEntity, OrderDTO.class);
		}

		//projectDto.setUserList(fetchUserList(projectDto.getUserList()));

		return orderDTO;

	}

	public OrderDTO updateOrder(OrderDTO projectDto) {
		OrderEntity savedOrderEntity = orderRepository.save(mapper.map(projectDto, OrderEntity.class));
		OrderDTO savedOrderDTO = mapper.map(savedOrderEntity, OrderDTO.class);

		//savedOrderDTO.setUserList(fetchUserList(savedOrderDTO.getUserList()));

		return savedOrderDTO;

	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);

	}

	public OrderDTO patchOrder(OrderPatchQuantityDTO orderPatchQuantityDTO) {
		OrderEntity orderToSave=null;
		Optional<OrderEntity> orderEntity = orderRepository.findById(orderPatchQuantityDTO.getId());
		// if (projectEntity!=null) {

		// projectEntity.setName(projectPatchNameDto.getName());
		// }

		if (orderEntity.isPresent() && orderPatchQuantityDTO.getQuantity()!=null){

			orderToSave=orderEntity.get();
			orderToSave.setQuantity( orderPatchQuantityDTO.getQuantity());
		}
		OrderEntity patchOrder = orderRepository.save(orderToSave);

		return mapper.map(patchOrder, OrderDTO.class);

	}

	/*public List<PriceDTO> fetchUserList(List<PriceDTO> userList) {

		List<PriceDTO> fetchedUserList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(userList)) {

			List<Integer> userIdList = userList.stream().map(PriceDTO::getId)
					.collect(Collectors.toList());

			 fetchedUserList = userService.getByUserIdList(userIdList);

		}
		return fetchedUserList;

	}*/

}
