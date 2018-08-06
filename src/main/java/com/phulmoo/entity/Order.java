package com.phulmoo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="orders")
@Entity
public class Order implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue
		private long OrderID;
		private long OrderUserID;
		private int OrderAmount;
		private String OrderShipName;
		private String OrderShipAddress;
		private String OrderShipAddress2;
		private String OrderCity;
		private String OrderState;
		private String OrderZip;
		private String OrderCountry;
		private String OrderPhone;
		private String OrderFax;
		private String OrderShipping;
		private String OrderTax;
		private String OrderEmail;
		private String OrderDate;
		private String OrderShipped;
		private long OrderTrackingNumber;
		public long getOrderID() {
			return OrderID;
		}
		public void setOrderID(long orderID) {
			OrderID = orderID;
		}
		public long getOrderUserID() {
			return OrderUserID;
		}
		public void setOrderUserID(long orderUserID) {
			OrderUserID = orderUserID;
		}
		public int getOrderAmount() {
			return OrderAmount;
		}
		public void setOrderAmount(int orderAmount) {
			OrderAmount = orderAmount;
		}
		public String getOrderShipName() {
			return OrderShipName;
		}
		public void setOrderShipName(String orderShipName) {
			OrderShipName = orderShipName;
		}
		public String getOrderShipAddress() {
			return OrderShipAddress;
		}
		public void setOrderShipAddress(String orderShipAddress) {
			OrderShipAddress = orderShipAddress;
		}
		public String getOrderShipAddress2() {
			return OrderShipAddress2;
		}
		public void setOrderShipAddress2(String orderShipAddress2) {
			OrderShipAddress2 = orderShipAddress2;
		}
		public String getOrderCity() {
			return OrderCity;
		}
		public void setOrderCity(String orderCity) {
			OrderCity = orderCity;
		}
		public String getOrderState() {
			return OrderState;
		}
		public void setOrderState(String orderState) {
			OrderState = orderState;
		}
		public String getOrderZip() {
			return OrderZip;
		}
		public void setOrderZip(String orderZip) {
			OrderZip = orderZip;
		}
		public String getOrderCountry() {
			return OrderCountry;
		}
		public void setOrderCountry(String orderCountry) {
			OrderCountry = orderCountry;
		}
		public String getOrderPhone() {
			return OrderPhone;
		}
		public void setOrderPhone(String orderPhone) {
			OrderPhone = orderPhone;
		}
		public String getOrderFax() {
			return OrderFax;
		}
		public void setOrderFax(String orderFax) {
			OrderFax = orderFax;
		}
		public String getOrderShipping() {
			return OrderShipping;
		}
		public void setOrderShipping(String orderShipping) {
			OrderShipping = orderShipping;
		}
		public String getOrderTax() {
			return OrderTax;
		}
		public void setOrderTax(String orderTax) {
			OrderTax = orderTax;
		}
		public String getOrderEmail() {
			return OrderEmail;
		}
		public void setOrderEmail(String orderEmail) {
			OrderEmail = orderEmail;
		}
		public String getOrderDate() {
			return OrderDate;
		}
		public void setOrderDate(String orderDate) {
			OrderDate = orderDate;
		}
		public String getOrderShipped() {
			return OrderShipped;
		}
		public void setOrderShipped(String orderShipped) {
			OrderShipped = orderShipped;
		}
		public long getOrderTrackingNumber() {
			return OrderTrackingNumber;
		}
		public void setOrderTrackingNumber(long orderTrackingNumber) {
			OrderTrackingNumber = orderTrackingNumber;
		}
		
}
