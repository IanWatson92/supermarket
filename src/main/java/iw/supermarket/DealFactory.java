package iw.supermarket;

import spark.*;

import java.util.Set;
import java.util.HashSet;

import java.util.logging.Logger;
import java.util.logging.Level;


import java.math.BigDecimal;

public class DealFactory {

	private static DealFactory factory = new DealFactory();

	private Logger _logger;

	public static final int BUYXGETYFREE = 1;
	public static final int BUYXFORY = 2;

	private int nextId;

	public DealFactory() {
		nextId = 100;
		_logger = Logger.getLogger(this.getClass().getName());
	}

	public int getNextId() {
		return ++nextId;
	}

	/**
	Creats a IDeal object by delegating call to a specific Deal creation method

	@return IDeal object created
	@throws Exception if an error occured or bad input
	**/
	public static IDeal createDeal(Request req, Service service)  throws Exception {

		Integer dealType = Integer.valueOf(req.queryParams("dealType"));
		switch (dealType) {

			case (BUYXGETYFREE):
				return factory.buyXGetYFreeDeal(req,service);
			case (BUYXFORY):
				return factory.discountItemDeal(req,service);
			default:
				throw new Exception ("DealType does not exist");
		}
	}

	public IDeal buyXGetYFreeDeal(Request req, Service service) throws Exception {
		_logger.log(Level.INFO,"buyXGetYFreeDeal");

		String[] itemNames = req.queryMap("items").values();

		Integer quantity = Integer.valueOf(req.queryParams("quantity"));

		Integer itemsFree = Integer.valueOf(req.queryParams("itemsFree"));
		_logger.log(Level.INFO,"items free " + itemsFree);

		Set<IItem> items = getItems(itemNames, service);
		
		IDeal deal;

		deal = new BuyXGetYFreeDeal(getNextId(), items, quantity, itemsFree);
		_logger.log(Level.INFO,"Deal -> " + deal.toString());

		return deal;
	}

	public IDeal discountItemDeal(Request req, Service service) throws Exception {
		_logger.log(Level.SEVERE,"buyXGetFor£Y");

		String[] itemNames = req.queryMap("items").values();

		Integer quantity = Integer.valueOf(req.queryParams("quantity"));

		BigDecimal discountPrice = new BigDecimal(req.queryParams("discountPrice"));
		
		Set<IItem> items = getItems(itemNames, service);

		IDeal deal;

		deal = new DiscountItemDeal(getNextId(), items, quantity, discountPrice);
		_logger.log(Level.INFO,"Deal -> " + deal.toString());

		return deal;
	}

	/**
	Gets the list of items for a deal. Validates whether items exist.

	@return true if valid
	@throws Exception when items don't exist invalid

	**/
	public Set<IItem> getItems(String[] itemNames, Service service) throws Exception{
		Set<IItem> items = new HashSet<IItem>();
		for (int i = 0; i < itemNames.length; i++) {
			IItem item = service.getItem(itemNames[i]);
			_logger.log(Level.INFO,"item name " + itemNames[i] + " is null, throwing client error");
			if (item == null) {
				throw new Exception("Item " + itemNames[i] + " does not exist");
			}
			items.add(item);
		}
		return items;
	}

}