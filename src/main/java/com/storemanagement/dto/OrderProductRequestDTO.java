package com.storemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductRequestDTO {

    @NonNull
    private Long productId;

    @NonNull
    private Double quantity;


}
