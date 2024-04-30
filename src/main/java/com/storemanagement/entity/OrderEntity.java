package com.storemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "orders")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double quantity;

	private LocalDateTime orderDate;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updatedAt;

	@ManyToOne
	private PriceEntity priceId;
	
	@ManyToOne
	private ProductEntity productId;
	

}