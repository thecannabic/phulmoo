package com.phulmoo.controller;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.phulmoo.BillingAPI;
import com.phulmoo.MailAPI;
import com.phulmoo.entity.Order;
import com.phulmoo.entity.PhulmooUser;
import com.phulmoo.modal.OrderUIModel;
import com.phulmoo.modal.PhulmooUserModal;
import com.phulmoo.modal.ProductModal;
import com.phulmoo.modal.ResponseEntity;
import com.phulmoo.services.PhulmooUserService;

@Component

@Path("/phulmooUser")
public class PhulmooUserRestResource {
	// final static Logger logger =(Logger)
	// LoggerFactory.logger(PhulmooUserRestResource.class);

	@Autowired(required = true)
	@Qualifier("phulmooUserService")
	private PhulmooUserService userService;

	// @CrossOriginResourceSharing(allowAllOrigins = true)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/confirmOrder")
	public ResponseEntity confirmOrder(OrderUIModel model) {
		System.out.println("confirmOrder:" + model.toString());
		Order order = null;
		ResponseEntity status = new ResponseEntity();
		try {
			order = userService.confirmOrder(model);
			status.setResponseCode(0);
			status.setResponseMessage("Order created successfully");
			status.setResponseData(order);

		} catch (Exception e) {
			status.setResponseCode(0);
			status.setResponseMessage("Error in Creating order:" + e.getMessage());
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/insertOrder")
	public ResponseEntity insertOrder(ProductModal model) {
		System.out.println("[insertOrder]" + model.toString());
		Order order = null;
		ResponseEntity status = new ResponseEntity();
		try {
			PhulmooUser user = new PhulmooUser();
			user.setUserID(Integer.parseInt(model.getProductSKU()));
			order = userService.insertOrderItem(model, user);
			status.setResponseCode(0);
			status.setResponseMessage("Order created successfully");
			status.setResponseData(order);

		} catch (Exception e) {
			status.setResponseCode(0);
			status.setResponseMessage("Error in Creating order:" + e.getMessage());
		}
		return status;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sample-user-json")
	public PhulmooUser getUser() {

		PhulmooUser PhulmooUser = new PhulmooUser();
		PhulmooUser.setUserEmail("satya@phulmoo.com");
		PhulmooUser.setUserFirstName("Satya");
		PhulmooUser.setUserLastName("Kharwara");
		PhulmooUser.setUserPassword("kuku2000");
		PhulmooUser.setUserID(2000);
		return PhulmooUser;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/generateBillingInfo")
	public ResponseEntity generateBillingInfo(OrderUIModel oum) {
		Order order = new Order();
		order.setOrderID(Integer.valueOf(oum.getAddress().getOrderId()));
		String path = BillingAPI.generatePDF(order, oum);
		// use the same file path to send mail from here
		ResponseEntity status = new ResponseEntity();
		if (path == null) {
			status.setResponseCode(-1);
			status.setResponseMessage("Failed");
		} else {
			status.setResponseCode(-1);
			status.setResponseMessage("Failed");
			status.setResponseData(path);
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sendMail")
	public ResponseEntity sendMail(String csv) {
		// order should be email, subject, message, file path for attachment if any
		String[] mailItems = csv.split(",");
		String email = mailItems[0];
		String subject = mailItems[1];
		String message = mailItems[2];
		String attachment = "";
		if (mailItems.length > 3)
			attachment = mailItems[3];

		// Order order = new Order();
		// order.setOrderID(Integer.valueOf(oum.getAddress().getOrderId()));
		// String path = BillingAPI.generatePDF(order, oum);
		MailAPI.sendMail(email, subject, message, attachment);
		// use the same file path to send mail from here
		ResponseEntity status = new ResponseEntity();
		status.setResponseCode(0);
		status.setResponseMessage("Failed");
		status.setResponseData("Done");
		return status;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user-info/{userID}")
	public PhulmooUser getUser(@PathParam("userID") long userId) {
		System.out.println("user-info:userID:" + userId);
		PhulmooUser user = userService.getUser(userId);
		return user;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create-user")
	public ResponseEntity createUser(PhulmooUserModal phulmooUserModal) {
		System.out.println("create User:" + phulmooUserModal.getEmail());
		PhulmooUser user = null;
		ResponseEntity status = new ResponseEntity();
		try {
			user = userService.addUser(phulmooUserModal.getFirstName(), phulmooUserModal.getLastName(),
					phulmooUserModal.getEmail(), phulmooUserModal.getSex(), phulmooUserModal.getPassword());
			phulmooUserModal.setEmail(user.getUserEmail());
			phulmooUserModal.setFirstName(user.getUserFirstName());
			phulmooUserModal.setLastName(user.getUserLastName());
			phulmooUserModal.setUserId(user.getUserID());
			status.setResponseCode(0);
			status.setResponseMessage("User Created Successfully");

		} catch (Exception e) {
			status.setResponseCode(0);
			status.setResponseMessage("Error in Creating users:" + e.getMessage());
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/token")
	public ResponseEntity token(PhulmooUserModal phulmooUserModal) {
		System.out.println("[token] [dada] [" + phulmooUserModal.toString() + "]");
		PhulmooUserModal user = null;
		ResponseEntity status = new ResponseEntity();
		try {
			user = userService.token(phulmooUserModal);
			if (user != null) {
				status.setResponseCode(0);
				status.setResponseMessage("User logged in");
				status.setResponseData(user);
			} else {
				status.setResponseCode(-1);
				status.setResponseMessage("Either username or password is invalid");
				status.setResponseData(user);
			}

		} catch (Exception e) {
			status.setResponseCode(-1);
			status.setResponseMessage("Failed to login : Either username or password is invalid");
			System.out.println(e.getMessage());
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/register")
	public ResponseEntity register(PhulmooUserModal phulmooUserModal) {
		System.out.println("[token] [dada] [" + phulmooUserModal.toString() + "]");
		PhulmooUserModal user = null;
		ResponseEntity status = new ResponseEntity();
		try {
			user = userService.register(phulmooUserModal);
			if (user != null) {
				status.setResponseCode(0);
				status.setResponseMessage("User registered");
				status.setResponseData(user);
			} else {
				status.setResponseCode(-1);
				status.setResponseMessage("Something went wrong");
			}

		} catch (Exception e) {
			status.setResponseCode(1);
			status.setResponseMessage("Failed to register :" + e.getMessage());
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productList")
	public ResponseEntity productList(String productType) {
		System.out.println("[productList] [dada] [" + productType + "]");
		ArrayList<ProductModal> products = new ArrayList<ProductModal>();
		ResponseEntity status = new ResponseEntity();
		try {
			products = userService.getProductList(productType);
			status.setResponseCode(0);
			status.setResponseMessage("User logged in");
			status.setResponseData(products);

		} catch (Exception e) {
			e.printStackTrace();
			status.setResponseCode(1);
			status.setResponseMessage("Failed to login :" + e.getMessage());
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/orderList")
	public ResponseEntity orderList(String userId) {
		System.out.println("[productList] [dada] [" + userId + "]");
		ArrayList<Order> orders = new ArrayList<Order>();
		ResponseEntity status = new ResponseEntity();
		try {
			orders = userService.getOrderList(userId);
			status.setResponseCode(0);
			status.setResponseMessage("User logged in");
			status.setResponseData(orders);

		} catch (Exception e) {
			e.printStackTrace();
			status.setResponseCode(1);
			status.setResponseMessage("Failed to login :" + e.getMessage());
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/orderDetail")
	public ResponseEntity orderDetail(String orderId) {
		// System.out.println("[orderDetail] [dada] [" + orderId + "]");
		OrderUIModel order = null;
		ResponseEntity status = new ResponseEntity();
		try {
			order = userService.getOrderDetail(orderId);
			status.setResponseCode(0);
			status.setResponseMessage("Processed OK");
			status.setResponseData(order);

		} catch (Exception e) {
			e.printStackTrace();
			status.setResponseCode(1);
			status.setResponseMessage("Failed to get order detail :" + e.getMessage());
		}
		return status;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/productDetail")
	public ResponseEntity productDetail(String productId) {
		System.out.println("[productDetail] [dada] [" + productId + "]");
		ProductModal product = new ProductModal();
		ResponseEntity status = new ResponseEntity();
		try {
			product = userService.productDetail(productId);
			status.setResponseCode(0);
			status.setResponseMessage("Sucess");
			status.setResponseData(product);

		} catch (Exception e) {
			e.printStackTrace();
			status.setResponseCode(1);
			status.setResponseMessage("Failed due to : " + e.getMessage());
		}
		return status;
	}
}
