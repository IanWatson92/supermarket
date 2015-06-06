package iw.supermarket;

import java.math.BigDecimal;

import java.util.Set;
import java.util.HashSet;

public class Deal implements IDeal {

	private Set<IItem> itemsNeeded;
	private int quantityNeeded;
	private int dealId;

	public Deal(int dealId, Set<IItem> itemsNeeded, int quantityNeeded) {
		this.dealId = dealId;
		this.itemsNeeded = itemsNeeded;
		this.quantityNeeded = quantityNeeded;
	}

	public boolean addItem(IItem item) {
		return itemsNeeded.add(item);
	}

	public boolean removeItem(IItem item) {
		return itemsNeeded.remove(item);
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

	@Override
	public String toString() {
		return "Deal [id:" + getDealId() +",items:" + getItemsNeeded().toString() + ",quantity:" + getQuantityNeeded() + "]";
	}

}