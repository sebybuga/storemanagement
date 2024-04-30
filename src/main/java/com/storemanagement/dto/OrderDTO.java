package com.storemanagement.dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderDTO {

	private Long id;

	private Double quantity;

	private LocalDateTime orderDate;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updatedAt;


	private PriceDTO priceId;

	private ProductDTO productId;



}
