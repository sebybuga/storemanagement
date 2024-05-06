package com.storemanagement.dto;

import com.storemanagement.constant.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
	private Long id;

	private String name;

	private String supplier;

	private String description;

	@Enumerated(EnumType.ORDINAL)
	private CurrencyEnum currencyId;

	private Double price;

	
}
