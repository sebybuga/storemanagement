package com.storemanagement.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.storemanagement.constant.OrderStatusEnum;
import com.storemanagement.dto.OrderDTO;
import com.storemanagement.dto.OrderProductRequestDTO;
import com.storemanagement.dto.OrderRequestDTO;
import com.storemanagement.entity.OrderEntity;
import com.storemanagement.entity.OrderProductEntity;
import com.storemanagement.entity.ProductEntity;
import com.storemanagement.repo.OrderProductRepository;
import com.storemanagement.repo.OrderRepository;
import com.storemanagement.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderProductRepository orderProductRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
    }

    public OrderDTO createOrder(OrderRequestDTO orderDTO) {

        return saveOrderInDatabase(orderDTO);

    }

    @Transactional
    private OrderDTO saveOrderInDatabase(OrderRequestDTO orderDTO) {
        log.info("orderEntity to be saved is :{}", orderDTO);

        OrderEntity orderEntity = null;
        OrderDTO savedOrderDTO = null;

        try {

            Long orderId = orderDTO.getId();
            if (orderId != null) {
                Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
                if (orderEntityOptional.isEmpty()) {
                    throw new Exception("Order id not found!");
                }

            }

            orderEntity = mapper.map(orderDTO, OrderEntity.class);

            List<OrderProductRequestDTO> orderProductRequestDTOList = orderDTO.getOrderProductList();

            if (orderProductRequestDTOList != null && !orderProductRequestDTOList.isEmpty()) {
                setOrderProductList(orderEntity, orderProductRequestDTOList);
            }

            OrderEntity savedOrder = orderRepository.save(orderEntity);

            savedOrderDTO = mapper.map(savedOrder, OrderDTO.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return savedOrderDTO;
    }


    public OrderDTO getOrder(Long id) {
        OrderDTO orderDTO = null;
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);

        if (orderEntity.isPresent()) {
            orderDTO = mapper.map(orderEntity.get(), OrderDTO.class);
        }

        return orderDTO;

    }


    public OrderDTO updateOrder(OrderRequestDTO orderDto) {

        return saveOrderInDatabase(orderDto);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);

    }


    private void setOrderProductList(OrderEntity orderEntity, List<OrderProductRequestDTO> orderProductRequestDTOList) {
        List<OrderProductEntity> orderProductEntityList = new ArrayList<>();

        orderEntity.getOrderProductList().clear();

        Long orderId = orderEntity.getId();
        if (orderId!=null) {
            orderProductRepository.deleteByOrderId(orderId);
        }

        for (OrderProductRequestDTO orderProductRequestDTO : orderProductRequestDTOList) {
            OrderProductEntity orderProductEntity = new OrderProductEntity();
            buildOrderProductEntity(
                    orderEntity,
                    orderProductEntity,
                    orderProductRequestDTO.getProductId(),
                    orderProductRequestDTO.getQuantity()

            );
            orderProductEntityList.add(orderProductEntity);

        }
        orderEntity.setOrderProductList(orderProductEntityList);

    }

    private void buildOrderProductEntity(OrderEntity orderEntity, OrderProductEntity orderProductEntity, Long productId, Double quantity) {
        Optional<ProductEntity> productEntity;
        if (orderEntity != null) {
            orderEntity.setOrderProductList(null);
            orderEntity.setOrderStatusId(OrderStatusEnum.INITIATED);
            orderProductEntity.setOrder(orderEntity);
        }
        if (productId != null) {
            productEntity = productRepository.findById(productId);
            if (productEntity.isPresent()) {
                orderProductEntity.setProduct(productEntity.get());
                orderProductEntity.setPrice(productEntity.get().getPrice());
                orderProductEntity.setCurrencyId(productEntity.get().getCurrencyId());
            }
        }
        if (quantity != null) {
            orderProductEntity.setQuantity(quantity);
        }


    }


}
