package com.storemanagement.entity;

import com.storemanagement.constant.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Table(name = "orders")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private LocalDateTime orderDate;

	private String createdBy;

	private LocalDateTime createdAt;

	private String updatedBy;

	private LocalDateTime updatedAt;

	@Enumerated(EnumType.ORDINAL)
	private OrderStatusEnum orderStatusId;

	@ManyToMany
	@JoinTable(
			name = "orders_products",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<ProductEntity> orderProductList;

	@ManyToMany
	@JoinTable(
			name = "orders_products",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id"))
	private  List<OrderProductEntity> quantities;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderEntity)) return false;
		OrderEntity that = (OrderEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(orderDate, that.orderDate) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedAt, that.updatedAt) && orderStatusId == that.orderStatusId && Objects.equals(orderProductList, that.orderProductList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, orderDate, createdBy, createdAt, updatedBy, updatedAt, orderStatusId, orderProductList);
	}

	@Override
	public String toString() {
		return "OrderEntity{" +
				"id=" + id +
				", orderDate=" + orderDate +
				", createdBy='" + createdBy + '\'' +
				", createdAt=" + createdAt +
				", updatedBy='" + updatedBy + '\'' +
				", updatedAt=" + updatedAt +
				", orderStatusId=" + orderStatusId +
				", orderProductList=" + orderProductList +
				'}';
	}
}