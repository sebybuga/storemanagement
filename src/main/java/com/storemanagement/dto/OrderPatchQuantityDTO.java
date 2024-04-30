package com.storemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderPatchQuantityDTO {

	private Long id;

	private Double quantity;


}
