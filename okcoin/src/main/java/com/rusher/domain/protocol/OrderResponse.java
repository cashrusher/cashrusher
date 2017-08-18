package com.rusher.domain.protocol;

public class OrderResponse extends Response {

	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
