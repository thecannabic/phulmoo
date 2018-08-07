package com.phulmoo.modal;

import java.io.Serializable;
import java.util.List;

public class OrderUIModel implements Serializable {
	private List<ProductModal> products;
	private Address address;

	public List<ProductModal> getProducts() {
		return products;
	}

	public void setProducts(List<ProductModal> products) {
		this.products = products;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String str = "OrderUIModel [products=";
		if (products != null) {
			for (int i = 0; i < products.size(); i++) {
				str += "{" + products.get(i).toString() + "}";
			}
		}
		str += "], address=" + address.toString() + "]";
		return str;
	}
}
