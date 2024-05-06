package com.storemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO  {

    private Long id;
    private ProductDTO product;
    private Double quantity;


}
