package iw.supermarket;

import java.util.Set;

public interface IDeal {

	public String getDealType();

	public int getDealId();
	public void setDealId(int dealId);

	public Set<IItem> getItemsNeeded();
	public void setItemsNeeded(Set<IItem> itemsNeeded);
	public boolean addItem(IItem item);
	public boolean removeItem(IItem item);

	public int getQuantityNeeded();
	public void setQuantityNeeded(int quantity);

	public String getDealInfo();

	public void applyDeal(IShoppingCart cart);

}