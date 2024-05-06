package com.storemanagement.entity;

import com.storemanagement.constant.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "orders_products")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="orderId",referencedColumnName = "id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="productId",referencedColumnName = "id")
    private ProductEntity product;

    private Double quantity;

    private Double price;

    @Enumerated(EnumType.ORDINAL)
    private CurrencyEnum currencyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductEntity)) return false;
        OrderProductEntity that = (OrderProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(product, that.product)
                && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && currencyId == that.currencyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, quantity, price, currencyId);
    }

    @Override
    public String toString() {
        return "OrderProductEntity{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", currencyId=" + currencyId +
                '}';
    }
}
