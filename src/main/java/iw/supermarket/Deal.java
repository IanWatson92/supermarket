package iw.supermarket;

import java.math.BigDecimal;

import java.util.Set;
import java.util.HashSet;

public class Deal implements IDeal {

	private Set<IItem> itemsNeeded;
	private int quantityNeeded;

	public Deal(Set<IItem> itemsNeeded, int quantityNeeded) {
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

}