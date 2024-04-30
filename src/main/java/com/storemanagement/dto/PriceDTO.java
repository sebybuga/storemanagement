package com.storemanagement.dto;

import com.storemanagement.constant.CurrencyEnum;
import com.storemanagement.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class PriceDTO {

private Integer id;

private Double priceValue;

private LocalDateTime priceDate;

private ProductEntity productId;

private CurrencyEnum currencyId;

}
