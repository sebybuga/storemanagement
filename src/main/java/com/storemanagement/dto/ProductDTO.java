package com.storemanagement.dto;

import java.util.List;

import com.storemanagement.entity.OrderEntity;
import com.storemanagement.entity.PriceEntity;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
	private Integer id;

	private String name;

	private String supplier;

	private String description;

	private List<PriceEntity> prices;

	private List<OrderEntity> orders;

	
}
