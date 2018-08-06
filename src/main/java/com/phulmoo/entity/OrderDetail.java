package com.phulmoo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

	@Table(name="orderdetails")
	@Entity
	public class OrderDetail implements Serializable {
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			@Id
			@GeneratedValue
			private long DetailID;
			private long DetailOrderID;
			private long DetailProductID;
			private String DetailOrderName;
			private String DetailName;
			private String DetailPrice;
			private String DetailSKU;
			private String DetailQuantity;
			public long getDetailID() {
				return DetailID;
			}
			public void setDetailID(long detailID) {
				DetailID = detailID;
			}
			public long getDetailOrderID() {
				return DetailOrderID;
			}
			public void setDetailOrderID(long detailOrderID) {
				DetailOrderID = detailOrderID;
			}
			public long getDetailProductID() {
				return DetailProductID;
			}
			public void setDetailProductID(long detailProductID) {
				DetailProductID = detailProductID;
			}
			public String getDetailOrderName() {
				return DetailOrderName;
			}
			public void setDetailOrderName(String detailOrderName) {
				DetailOrderName = detailOrderName;
			}
			public String getDetailName() {
				return DetailName;
			}
			public void setDetailName(String detailName) {
				DetailName = detailName;
			}
			public String getDetailPrice() {
				return DetailPrice;
			}
			public void setDetailPrice(String detailPrice) {
				DetailPrice = detailPrice;
			}
			public String getDetailSKU() {
				return DetailSKU;
			}
			public void setDetailSKU(String detailSKU) {
				DetailSKU = detailSKU;
			}
			public String getDetailQuantity() {
				return DetailQuantity;
			}
			public void setDetailQuantity(String detailQuantity) {
				DetailQuantity = detailQuantity;
			}
     }
