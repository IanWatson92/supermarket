package iw.supermarket;

import java.math.BigDecimal;

import java.util.Set;
import java.util.HashSet;

public class BuyXGetYFreeDeal implements IDeal {

	private Set<IItem> itemsNeeded;
	private int quantityNeeded;
	private int dealId;

	private int itemsFree;
	private String dealInfo;

	private String dealType = "Buy X Get Y Free";

	public BuyXGetYFreeDeal(int dealId, Set<IItem> itemsNeeded, int quantityNeeded, int itemsFree) {
		this.dealId = dealId;
		this.itemsNeeded = itemsNeeded;
		this.quantityNeeded = quantityNeeded;
		this.itemsFree = itemsFree;
		dealInfo = itemsFree + " items free";
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

	public int getItemsFree() {
		return itemsFree;
	}

	public void setItemsFree(int itemsFree) {
		this.itemsFree = itemsFree;
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