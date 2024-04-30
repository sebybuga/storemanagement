package com.storemanagement.entity;

import com.storemanagement.constant.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "prices")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    private Double priceValue;

	private LocalDateTime priceDate;

    @ManyToOne
    private ProductEntity productId;

    @ManyToOne
    @Enumerated(EnumType.ORDINAL)
    private CurrencyEnum currencyId;

    
}