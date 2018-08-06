package com.phulmoo.modal;

public class ProductModal {
	private long ProductID;
	private String ProductSKU;
	private String ProductName;
	private String ProductPrice;
	private String ProductWeight;
	private String ProductCartDesc;
	private String ProductShortDesc;
	private String ProductLongDesc;
	private String ProductThumb;
	private String ProductImage;
	private String ProductCategoryID;
	private String ProductUpdateDate;
	private String ProductStock;
	private String ProductLive;
	private String ProductUnlimited;
	private String ProductLocation;
	private int OrderQuantity;

	public long getProductID() {
		return ProductID;
	}

	public void setProductID(long productID) {
		ProductID = productID;
	}

	public String getProductSKU() {
		return ProductSKU;
	}

	public void setProductSKU(String productSKU) {
		ProductSKU = productSKU;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductPrice() {
		return ProductPrice;
	}

	public void setProductPrice(String productPrice) {
		ProductPrice = productPrice;
	}

	public String getProductWeight() {
		return ProductWeight;
	}

	public void setProductWeight(String productWeight) {
		ProductWeight = productWeight;
	}

	public String getProductCartDesc() {
		return ProductCartDesc;
	}

	public void setProductCartDesc(String productCartDesc) {
		ProductCartDesc = productCartDesc;
	}

	public String getProductShortDesc() {
		return ProductShortDesc;
	}

	public void setProductShortDesc(String productShortDesc) {
		ProductShortDesc = productShortDesc;
	}

	public String getProductLongDesc() {
		return ProductLongDesc;
	}

	public void setProductLongDesc(String productLongDesc) {
		ProductLongDesc = productLongDesc;
	}

	public String getProductThumb() {
		return ProductThumb;
	}

	public void setProductThumb(String productThumb) {
		ProductThumb = productThumb;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	public String getProductCategoryID() {
		return ProductCategoryID;
	}

	public void setProductCategoryID(String productCategoryID) {
		ProductCategoryID = productCategoryID;
	}

	public String getProductUpdateDate() {
		return ProductUpdateDate;
	}

	public void setProductUpdateDate(String productUpdateDate) {
		ProductUpdateDate = productUpdateDate;
	}

	public String getProductStock() {
		return ProductStock;
	}

	public void setProductStock(String productStock) {
		ProductStock = productStock;
	}

	public String getProductLive() {
		return ProductLive;
	}

	public void setProductLive(String productLive) {
		ProductLive = productLive;
	}

	public String getProductUnlimited() {
		return ProductUnlimited;
	}

	public void setProductUnlimited(String productUnlimited) {
		ProductUnlimited = productUnlimited;
	}

	public String getProductLocation() {
		return ProductLocation;
	}

	public void setProductLocation(String productLocation) {
		ProductLocation = productLocation;
	}

	public int getOrderQuantity() {
		return OrderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		OrderQuantity = orderQuantity;
	}

	@Override
	public String toString() {
		return "ProductModal [ProductID=" + ProductID + ", ProductSKU=" + ProductSKU + ", ProductName=" + ProductName
				+ ", ProductPrice=" + ProductPrice + ", ProductWeight=" + ProductWeight + ", ProductCartDesc="
				+ ProductCartDesc + ", ProductShortDesc=" + ProductShortDesc + ", ProductLongDesc=" + ProductLongDesc
				+ ", ProductThumb=" + ProductThumb + ", ProductImage=" + ProductImage + ", ProductCategoryID="
				+ ProductCategoryID + ", ProductUpdateDate=" + ProductUpdateDate + ", ProductStock=" + ProductStock
				+ ", ProductLive=" + ProductLive + ", ProductUnlimited=" + ProductUnlimited + ", ProductLocation="
				+ ProductLocation + "]";
	}

}
