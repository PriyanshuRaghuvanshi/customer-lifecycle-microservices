package com.nagarro.shoppingcart.model;



import java.math.BigDecimal;


public class Product {

    private Long id; 
    private String rateplanID;  
    private String rateplan;
    private BigDecimal price;

	public Product() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRateplanID() {
		return rateplanID;
	}

	public void setRateplanID(String rateplanID) {
		this.rateplanID = rateplanID;
	}

	public String getRateplan() {
		return rateplan;
	}

	public void setRateplan(String rateplan) {
		this.rateplan = rateplan;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", rateplanID=" + rateplanID + ", rateplan=" + rateplan + ", price=" + price + "]";
	}



}