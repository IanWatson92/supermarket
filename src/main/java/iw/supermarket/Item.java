package iw.supermarket;

import java.math.BigDecimal;

public class Item implements IItem {

	String name;
	BigDecimal price;
	BigDecimal weight;

	boolean isPricePerWeight = false;

	public Item(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public Item(String name, BigDecimal price, BigDecimal weight) {
		this.name = name;
		this.price = price;
		this.weight = weight;
		isPricePerWeight = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isPricePerWeight() {
		return isPricePerWeight;
	}

	public BigDecimal getWeight() {
		return weight;
	}
	
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

}