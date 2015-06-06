package iw.supermarket;

import java.math.BigDecimal;

import java.util.Set;
import java.util.HashSet;

public class DiscountItemDeal implements IDeal {

	private Set<IItem> itemsNeeded;
	private int quantityNeeded;
	private int dealId;

	private BigDecimal discountPrice;
	private String dealInfo;

	private String dealType = "Buy X For Y";

	public DiscountItemDeal(int dealId, Set<IItem> itemsNeeded, int quantityNeeded, BigDecimal discountPrice) {
		this.dealId = dealId;
		this.itemsNeeded = itemsNeeded;
		this.quantityNeeded = quantityNeeded;
		this.discountPrice = discountPrice;
		dealInfo = "Buy " + quantityNeeded + " for &pound;" + discountPrice;
	}

	public boolean addItem(IItem item) {
		return itemsNeeded.add(item);
	}

	public boolean removeItem(IItem item) {
		return itemsNeeded.remove(item);
	}

	public String getDealInfo() {
		return dealInfo;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Set<IItem> getItemsNeeded() {
		return itemsNeeded;
	}

	public void setItemsNeeded(Set<IItem> itemsNeeded) {
		this.itemsNeeded = itemsNeeded;
	}

	public int getQuantityNeeded() {
		return quantityNeeded;
	}

	public void setQuantityNeeded(int quantity) {
		this.quantityNeeded = quantity;
	}

	public void applyDeal(IShoppingCart cart) {
		return;
	}

	public int getDealId() {
		return dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public String getDealType() {
		return dealType;
	}

	@Override
	public String toString() {
		return "Deal [id:" + getDealId() +",items:" + getItemsNeeded().toString() + ",quantity:" + getQuantityNeeded() + "]";
	}

}