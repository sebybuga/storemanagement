package com.storemanagement.dto;

import java.util.List;

import com.storemanagement.constant.CurrencyEnum;
import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
	private Integer id;

	private String name;

	private String supplier;

	private String description;

	@Enumerated(EnumType.ORDINAL)
	private CurrencyEnum currencyId;

	private Double price;

	
}
