package iw.supermarket;

import java.math.BigDecimal;

public interface IItem {

	public String getName();
	public void setName(String name);

	public BigDecimal getPrice();
	public void setPrice(BigDecimal price);

	public boolean isPricePerWeight();
	public BigDecimal getWeight();
	public void setWeight(BigDecimal price);

}