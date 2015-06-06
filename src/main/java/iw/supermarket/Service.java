package iw.supermarket;

import static spark.Spark.*;

import spark.*;

import java.util.Set;
import java.util.HashSet;

import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

import java.util.logging.Logger;
import java.util.logging.Level;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import java.math.BigDecimal;

import java.util.Collection;


public class Service {

	private Logger _logger;
	private Items items;
	private Deals deals;

	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

	public Service() {
		items = new Items();
		deals = new Deals();
		_logger = Logger.getLogger(this.getClass().getName());

		staticFileLocation("/public");
		try {
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("resourcesPath")));
		} catch (IOException e) {
			_logger.log(Level.SEVERE,"Exception thrown", e);
		}

		setupItems();
		setupDeals();

		exception(Exception.class, (e, request, response) -> {
   			response.status(400);
    		response.body("Exception -> " + e.getMessage());
		});


    	// replaced with static html for now
    	// get("/", (req, res) -> {
    	// 	Map<String,Object> attributes = new HashMap<>();
    	// 	attributes.put("message","Hello!");
    	// 	attributes.put("items",items.getItems());
    	// 	return new ModelAndView(attributes,"home.ftl");
    	// }, new FreeMarkerEngine(cfg));

	}


	int nextId = 1;
	public void setupDeals() {

		get("/deals/types", "application/json", (req, res) -> {
    		return getDealTypes();
    	}, new JsonTransformer());

		get("/deals", "application/json", (req, res) -> {
    		return getDeals();
    	}, new JsonTransformer());

    	get("/deals/:id", "application/json", (req, res) -> {
    		Integer id = Integer.valueOf(req.params(":id"));
    		return getDeal(id);
    	}, new JsonTransformer());

    	post("/deals", "application/json", (req, res) -> {
    		_logger.log(Level.INFO,"Post deal called");
			
			String type = req.queryParams("dealTypes");

			IDeal deal;
			if(type.equals("Buy X Get Y Free")) {
				_logger.log(Level.INFO,"Type is Buy X Get Y Free");
				deal = buyXGetYFreeDeal(req);
			} else {
				_logger.log(Level.SEVERE,"Type: " + type + " not found");
				throw new Exception("Deal not found!");
			}

			res.status(201);
			return deal;
		}, new JsonTransformer());
	}

	public IDeal buyXGetYFreeDeal(Request req) throws Exception {
		_logger.log(Level.SEVERE,"buyXGetYFreeDeal");

		String[] itemNames = req.queryMap("items").values();

		Integer quantity = Integer.valueOf(req.queryParams("quantity"));

		Integer itemsFree = Integer.valueOf(req.queryParams("itemsFree"));
		_logger.log(Level.INFO,"items free " + itemsFree);

		Set<IItem> items = new HashSet<IItem>();
		for (int i = 0; i < itemNames.length; i++) {
			IItem item = getItem(itemNames[i]);
			_logger.log(Level.INFO,"item name " + itemNames[i] + " is null, throwing client error");
			if (item == null) {
				throw new Exception("Item " + itemNames[i] + " does not exist");
			}
			items.add(item);
		}
		
		IDeal deal;
		nextId++;
		// need to deal with id better here

		deal = new Deal(nextId, items, quantity, itemsFree);
		_logger.log(Level.INFO,"Deal -> " + deal.toString());

		setDeal(deal);
		return deal;
	}

	public void setupItems() {

		post("/items", "application/json", (req, res) -> {
			String name = req.queryParams("name");
			String priceParam = req.queryParams("price");
			BigDecimal price = new BigDecimal(priceParam);

			String weightParam = req.queryParams("weight");

			IItem item;

			if (weightParam == null || weightParam.isEmpty()) {
				_logger.log(Level.INFO,"WeightParam not set");
				item = new Item(name,price);
			} else {
				_logger.log(Level.INFO,"WeightParam set");
				BigDecimal weight = new BigDecimal(weightParam);
				item = new Item(name,price,weight);
			}

			setItem(item);

			res.status(201);
			return item;
		}, new JsonTransformer());


    	get("/items/:name", "application/json", (req, res) -> {
    		String name = req.params(":name");
    		return getItem(name);
    	}, new JsonTransformer());

    	get("/items", "application/json", (req, res) -> {
    		return getItems();
    	}, new JsonTransformer());
	}

	public void setItem(IItem item) {
		items.setItem(item);
	}

	public IItem getItem(String name) {
		return items.getItem(name);
	}

	public Collection getItems() {
		return items.getItems().values();
	}

	public void stopServer() {
		spark.Spark.stop();
	}

	public Collection getDeals() {
		return deals.getDeals().values();
	}

	// hardcode for now...
	public Collection getDealTypes() {
		ArrayList list = new ArrayList<String>();
		list.add("Buy X Get Y Free");
		return list;
	}

	public IDeal getDeal(Integer id) {
		return deals.getDeal(id);
	}

	public void setDeal(IDeal deal) {
		deals.setDeal(deal);
	}

	public static void main(String[] args) {
		Service service = new Service();
		IItem apple = new Item("apple",new BigDecimal(12));
		IItem banana = new Item("banana",new BigDecimal(10));

		service.items.setItem(apple);
		service.items.setItem(banana);
		
		Set<IItem> items = new HashSet<IItem>();
		items.add(apple);
		items.add(banana);
		service.deals.setDeal(new Deal(1,items,2,1));
    }
}
