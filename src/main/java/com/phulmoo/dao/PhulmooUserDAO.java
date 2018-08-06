package com.phulmoo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.phulmoo.entity.Order;
import com.phulmoo.entity.OrderDetail;
import com.phulmoo.entity.PhulmooUser;
import com.phulmoo.entity.Product;
import com.phulmoo.entity.Session;
import com.phulmoo.modal.OrderUIModel;
import com.phulmoo.modal.PhulmooUserModal;
import com.phulmoo.modal.ProductModal;

@Repository("userDao")
public interface PhulmooUserDAO {

	
	public PhulmooUser addUser(PhulmooUser user);
	public PhulmooUser getUser(long userId); 
	public PhulmooUser getUser(String email);
	public Session token(PhulmooUserModal userInput);
	public ArrayList<ProductModal> getProductList(String productType);
	//public Response getImage(String imageId);
	public PhulmooUser register(PhulmooUserModal input);
	public ProductModal getProductDetail(String pid);
	public Order confirmOrder(OrderUIModel model);
	public Order insertOrderItem(Order order, OrderDetail orderDetail);
	public ArrayList<Order> getOrderList(int userId);
	public OrderUIModel getOrderDetail(int id);
}
