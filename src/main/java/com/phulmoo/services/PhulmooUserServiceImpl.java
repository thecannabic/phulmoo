package com.phulmoo.services;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.phulmoo.dao.PhulmooUserDAO;
import com.phulmoo.entity.Order;
import com.phulmoo.entity.OrderDetail;
import com.phulmoo.entity.PhulmooUser;
import com.phulmoo.entity.Session;
import com.phulmoo.modal.OrderUIModel;
import com.phulmoo.modal.PhulmooUserModal;
import com.phulmoo.modal.ProductModal;

@Service("phulmooUserService")
@Transactional(propagation = Propagation.REQUIRED)
public class PhulmooUserServiceImpl implements PhulmooUserService {

	@Autowired(required = true)
	private PhulmooUserDAO userDAO;

	public PhulmooUser getUser(long userId) {
		return userDAO.getUser(userId);
	}

	public PhulmooUser getUser(String email) {
		return userDAO.getUser(email);
	}

	public PhulmooUser addUser(String firstName, String lastName, String email, String sex, String password) {
		PhulmooUser user = new PhulmooUser();
		user.setUserRegistrationDate(new Date().toString());
		user.setUserEmail(email);
		user.setUserFirstName(firstName);
		user.setUserLastName(lastName);
		user.setUserPassword(password);
		return userDAO.addUser(user);
	}

	public PhulmooUserModal token(PhulmooUserModal userInput) {
		Session session = userDAO.token(userInput);
		if (session != null) {
			userInput.setToken(session.getToken());
			userInput.setPassword(null);
			userInput.setUserId(session.getUserID());
			userInput.setFirstName(session.getEmail());
			return userInput;
		}
		return null;
	}

	public ArrayList<ProductModal> getProductList(String productType) {
		ArrayList<ProductModal> products = userDAO.getProductList(productType);
		return products;
	}

	public Response getImage(String imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	public PhulmooUserModal register(PhulmooUserModal input) {
		PhulmooUser userObj = userDAO.register(input);

		if (userObj != null) {
			PhulmooUserModal output = new PhulmooUserModal();
			output.setUsername(userObj.getUsername());
			output.setUserId(userObj.getUserID());
			return output;
		}
		return null;
	}

	public ProductModal productDetail(String pid) {
		ProductModal product = userDAO.getProductDetail(pid);
		// TODO Auto-generated method stub
		return product;
	}

	public Order confirmOrder(OrderUIModel model) {
		Order order = userDAO.confirmOrder(model);
		return order;
	}

	public Order insertOrderItem(ProductModal model, PhulmooUser user) {
		Order order = new Order();
		order.setOrderUserID(user.getUserID());
		order.setOrderAmount(Integer.parseInt(model.getProductPrice()));
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setDetailPrice(model.getProductPrice());
		orderDetail.setDetailProductID(model.getProductID());
		orderDetail.setDetailQuantity("1");

		return userDAO.insertOrderItem(order, orderDetail);
	}

	public ArrayList<Order> getOrderList(String userId) {
		ArrayList<Order> orders = userDAO.getOrderList(Integer.parseInt(userId));
		return orders;
	}

	public OrderUIModel getOrderDetail(String orderId) {
		int id = Integer.valueOf(orderId);
		OrderUIModel model = userDAO.getOrderDetail(id);

		return model;
	}

}
