package com.phulmoo.services;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.phulmoo.entity.Order;
import com.phulmoo.entity.PhulmooUser;
import com.phulmoo.modal.OrderUIModel;
import com.phulmoo.modal.PhulmooUserModal;
import com.phulmoo.modal.ProductModal;



public interface PhulmooUserService {
	public PhulmooUser getUser(long userId);
	public PhulmooUser getUser(String email);
	public PhulmooUser addUser(String firstName,String lastName,String email,String sex,String password);
	public PhulmooUserModal token(PhulmooUserModal userInput);
	public ArrayList<ProductModal> getProductList(String productType);
	public Response getImage(String imageId);
	public PhulmooUserModal register(PhulmooUserModal input);
	public ProductModal productDetail(String pid);
	public Order confirmOrder(OrderUIModel model);
	public Order insertOrderItem(ProductModal model,PhulmooUser user);
	public ArrayList<Order> getOrderList(String userId);
	public OrderUIModel getOrderDetail(String orderId);
}
