package com.phulmoo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.phulmoo.entity.Order;
import com.phulmoo.entity.OrderDetail;
import com.phulmoo.entity.PhulmooUser;
import com.phulmoo.entity.Product;
import com.phulmoo.entity.Session;
import com.phulmoo.modal.Address;
import com.phulmoo.modal.OrderUIModel;
import com.phulmoo.modal.PhulmooUserModal;
import com.phulmoo.modal.ProductModal;

@Service("phulmooUserDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class PhulmooUserDAOImpl implements PhulmooUserDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional(readOnly = false)
	public PhulmooUser addUser(PhulmooUser user) {
		entityManager.persist(user);
		return user;
	}

	@Transactional(readOnly = true)
	public PhulmooUser getUser(long UserID) {
		Query query = entityManager.createQuery("from PhulmooUser where UserID= :ID");
		query.setParameter("ID", UserID);
		return (PhulmooUser) query.getSingleResult();
	}

	@Transactional(readOnly = true)
	public PhulmooUser getUser(String email) {
		return (PhulmooUser) entityManager.createQuery("from PhulmooUser where UserEmail= :email")
				.setParameter("email", email).getSingleResult();
	}

	@Transactional(readOnly = true)
	public PhulmooUser getUserById(long userId) {

		Query query = entityManager.createQuery("from PhulmooUser where UserID= :ID");
		query.setParameter("ID", userId);
		return (PhulmooUser) query.getSingleResult();
	}

	public Session token(PhulmooUserModal userInput) {
		Query query = entityManager.createQuery("from PhulmooUser where Username= :uname");
		query.setParameter("uname", userInput.getUsername());
		PhulmooUser user = (PhulmooUser) query.getSingleResult();
		if (user != null) {
			query = entityManager.createQuery("from Session where UserID= :ID");
			query.setParameter("ID", user.getUserID());
			return (Session) query.getSingleResult();
		}
		return null;
	}

	public ArrayList<ProductModal> getProductList(String userInput) {
		Query query = null;
		if (userInput != null && userInput.length() > 0) {
			query = entityManager.createQuery("from Product where ProductCategoryID= :Ptype");
			query.setParameter("Ptype", userInput);
		} else {
			query = entityManager.createQuery("from Product where ProductID > 0");
		}
		ArrayList<ProductModal> products = (ArrayList<ProductModal>) query.getResultList();
		return products;
	}

	@Transactional
	public PhulmooUser register(PhulmooUserModal input) {
		PhulmooUser dbTypeInput = new PhulmooUser();
		dbTypeInput.setUserEmail(input.getEmail());
		dbTypeInput.setUserFirstName(input.getFirstName());
		dbTypeInput.setUserLastName(input.getLastName());
		dbTypeInput.setUserPassword(input.getPassword());
		dbTypeInput.setUsername(input.getEmail());

		entityManager.persist(dbTypeInput);
		dbTypeInput = entityManager.find(PhulmooUser.class, dbTypeInput.getUserEmail());
		entityManager.refresh(dbTypeInput);
		return dbTypeInput;
	}

	public ProductModal getProductDetail(String pid) {
		Product product = new Product();
		product.setProductID(Integer.valueOf(pid));
		product = entityManager.find(Product.class, product.getProductID());
		ProductModal model = new ProductModal();
		model.setProductID(product.getProductID());
		model.setProductPrice(product.getProductPrice());
		model.setProductName(product.getProductName());
		model.setProductCartDesc(product.getProductCartDesc());
		model.setProductLongDesc(product.getProductLongDesc());
		model.setProductShortDesc(product.getProductShortDesc());
		model.setProductStock(product.getProductStock());
		return model;
	}

	@Transactional
	public Order confirmOrder(OrderUIModel model) {
		Address uiAddress = model.getAddress();
		List<ProductModal> products = model.getProducts();
		Order order = new Order();
		order.setOrderID(Integer.valueOf(uiAddress.getOrderId()));
		order.setOrderShipped("0");
		order.setOrderShipping("1");
		order.setOrderEmail(uiAddress.getEmailAddress());
		order.setOrderShipName(uiAddress.getName());
		order.setOrderShipAddress(uiAddress.getHomeAddress());
		order.setOrderCity(uiAddress.getCity());
		order.setOrderZip(uiAddress.getPostalCode());
		order.setOrderPhone(uiAddress.getPhoneNumber());
		order.setOrderCountry("India");
		order.setOrderState("HP");
		order.setOrderFax("");
		order.setOrderTax("0");
		
		order.setOrderShipAddress2("");
		Order newObj=entityManager.merge(order);
		
		
		
		
		
		
		//entityManager.refresh(order);
		//entityManager.
		order=newObj;

		System.out.println("Customer order updated: [" + order.getOrderID() + "]");

		Query query = null;
		query = entityManager.createQuery("from OrderDetail where DetailOrderID= :orderId");
		query.setParameter("orderId", Long.valueOf(order.getOrderID()));
		List<OrderDetail> orderDetails = (List<OrderDetail>) query.getResultList();

		List<OrderDetail> finalDetails = new ArrayList<OrderDetail>();

		for (int i = 0; i < products.size(); i++) {
			ProductModal productModal = products.get(i);
			for (int j = 0; j < orderDetails.size(); j++) {
				OrderDetail od = orderDetails.get(j);
				if (productModal.getProductID() == od.getDetailProductID()) {
					// any coupon related calculation must go here
					od.setDetailPrice(productModal.getProductPrice());
					od.setDetailQuantity(productModal.getOrderQuantity() + "");
					od.setDetailOrderName(order.getOrderShipName());
					finalDetails.add(od);
					break;
				}
			}
		}
		if (finalDetails.size() != orderDetails.size()) {
			// possibly some of the items were removed the need to be deleted
			for (int i = 0; i < orderDetails.size(); i++) {
				OrderDetail oldDetail = orderDetails.get(i);
				boolean itemRemoved = false;
				for (int j = 0; j < finalDetails.size(); j++) {
					OrderDetail finalDetail = finalDetails.get(j);
					if (finalDetail.getDetailID() == oldDetail.getDetailID()) {
						itemRemoved = false;
						break;
					} else {
						itemRemoved = true;
					}
				}
				if (itemRemoved) {
					entityManager.remove(oldDetail);
				}
			}
		}
		{// update the order detail data finally
			for (int i = 0; i < finalDetails.size(); i++) {
				OrderDetail orderDetail = finalDetails.get(i);
				entityManager.merge(orderDetail);
			}
		}

		// for (int i = 0; i < products.size(); i++) {
		// // need to update order items and quantity here
		// ProductModal pm = products.get(i);
		// OrderDetail orderdetail = new OrderDetail();
		// orderdetail.setDetailID(0);
		// orderdetail.setDetailName(order.getOrderShipName());
		// orderdetail.setDetailOrderID(order.getOrderID());
		// orderdetail.setDetailOrderName(order.getOrderShipName());
		// orderdetail.setDetailPrice(pm.getProductPrice());
		// orderdetail.setDetailProductID(pm.getProductID());
		// orderdetail.setDetailQuantity("1");// TODO change this to data from UI
		// orderdetail.setDetailSKU("");
		// entityManager.persist(orderdetail);
		// }
		// if (order.getOrderID() > 0)
		// return order;
		// else
		//entityManager.refresh(order);
		return order;
	}

	@Transactional
	public Order insertOrderItem(Order order, OrderDetail detail) {
		order.setOrderShipped("0");
		PhulmooUser user = entityManager.find(PhulmooUser.class, order.getOrderUserID());
		order.setOrderCity(user.getUserCity());
		order.setOrderAmount(1);
		order.setOrderCountry(user.getUserCountry());
		order.setOrderEmail(user.getUserEmail());
		order.setOrderPhone(user.getUserPhone());
		order.setOrderShipName(user.getUsername());
		order.setOrderShipping("0");
		order.setOrderShipAddress(user.getUserAddress());
		order.setOrderShipAddress2(user.getUserAddress2());
		order.setOrderFax("");
		order.setOrderTax("0");
		order.setOrderState(user.getUserState());
		order.setOrderZip(user.getUserZip());

		entityManager.persist(order);
		entityManager.refresh(order);

		if (order.getOrderID() > 0) {
			System.out.println("inserted object id is =" + order.getOrderID());
			detail.setDetailOrderID(order.getOrderID());
			detail.setDetailName("");
			detail.setDetailOrderName("");
			detail.setDetailSKU("");

			entityManager.persist(detail);
			// return order;
		}

		return order;
	}

	public ArrayList<Order> getOrderList(int userId) {
		Query query = null;
		if (true) {
			query = entityManager.createQuery("from Order where OderUserID= :userId");
			query.setParameter("userId", userId);
		}
		ArrayList<Order> orders = (ArrayList<Order>) query.getResultList();
		return orders;
	}

	public OrderUIModel getOrderDetail(int id) {
		Query query = null;
		if (true) {
			query = entityManager.createQuery("from OrderDetail where DetailOrderID= :orderId");
			query.setParameter("orderId", Long.valueOf(id));
		}

		List<OrderDetail> orderDetailList = (List<OrderDetail>) query.getResultList();
		List<ProductModal> products = new ArrayList<ProductModal>();
		Address address = new Address();
		address.setOrderId(id + "");
		for (int i = 0; i < orderDetailList.size(); i++) {
			OrderDetail detail = orderDetailList.get(i);
			ProductModal product = getProductDetail(detail.getDetailProductID() + "");
			products.add(product);
		}
		OrderUIModel model = new OrderUIModel();
		model.setAddress(address);
		model.setProducts(products);
		return model;
	}

}
