package com.storemanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.storemanagement.dto.OrderDTO;
import com.storemanagement.dto.OrderPatchStatusDTO;
import com.storemanagement.dto.OrderProductDTO;
import com.storemanagement.dto.OrderRequestDTO;
import com.storemanagement.entity.OrderEntity;
import com.storemanagement.entity.OrderProductEntity;
import com.storemanagement.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
	//@Value("${usermanagement.user.getAllByIds}")
	//private String usermanagementGetAllByIdsUrl;
	private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	//private final ObjectMapper mapper = new ObjectMapper();
	private OrderRepository orderRepository;
	//private UserService userService;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;

	}

	public OrderDTO createOrder(OrderRequestDTO orderDTO) {
		log.info("orderEntity to be created is :{}",orderDTO);
		OrderEntity orderEntity = mapper.map(orderDTO, OrderEntity.class);

		OrderEntity savedOrder = orderRepository.save(orderEntity);

		OrderDTO savedOrderDTO = mapper.map(savedOrder, OrderDTO.class);

		return savedOrderDTO;

	}



	public OrderDTO getOrder(Long id) {

		OrderDTO orderDTO=null;

		Optional<OrderEntity> orderEntity = orderRepository.findById(id);

		if (orderEntity.isPresent()) {
			//log.debug("orderEntity is :{}",orderEntity.toString());

			orderDTO = mapper.map(orderEntity.get(), OrderDTO.class);

			setProductsQuantities(orderEntity.get(),orderDTO);
		}




		return orderDTO;

	}

	private void setProductsQuantities(OrderEntity orderEntity, OrderDTO orderDTO) {
		 List<OrderProductEntity> orderProductEntityList =  orderEntity.getQuantities();
		List<OrderProductDTO> orderProductDTOList = new ArrayList<>();
		for (OrderProductEntity orderProductEntity :  orderProductEntityList){
			OrderProductDTO orderProductDTO = new OrderProductDTO(
					orderProductEntity.getProduct().getId(),
					orderProductEntity.getQuantity());
			orderProductDTOList.add(orderProductDTO);


		}
		orderDTO.setQuantities(orderProductDTOList);

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

	public OrderDTO patchOrder(OrderPatchStatusDTO orderPatchStatusDTO) {
		OrderEntity orderToSave=null;
		Optional<OrderEntity> orderEntity = orderRepository.findById(orderPatchStatusDTO.getId());

		if (orderEntity.isPresent() && orderPatchStatusDTO.getOrderStatusId()!=null){
			orderToSave=orderEntity.get();
			orderToSave.setOrderStatusId( orderPatchStatusDTO.getOrderStatusId());
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
