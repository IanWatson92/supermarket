package iw.supermarket;

import static spark.Spark.*;

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

	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

	public Service() {
		items = new Items();
		_logger = Logger.getLogger(this.getClass().getName());

		staticFileLocation("/public");
		try {
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("resourcesPath")));
		} catch (IOException e) {
			_logger.log(Level.SEVERE,"Exception thrown", e);
		}

		post("/items", "application/json", (req, res) -> {
			String name = req.queryParams("name");
			String priceParam = req.queryParams("price");
			BigDecimal price = new BigDecimal(priceParam);

			IItem item = new Item(name,price);
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

    	// replaced with static html for now
    	// get("/", (req, res) -> {
    	// 	Map<String,Object> attributes = new HashMap<>();
    	// 	attributes.put("message","Hello!");
    	// 	attributes.put("items",items.getItems());
    	// 	return new ModelAndView(attributes,"home.ftl");
    	// }, new FreeMarkerEngine(cfg));

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

	public static void main(String[] args) {
		Service service = new Service();
		service.items.setItem(new Item("apple",new BigDecimal(12)));
		service.items.setItem(new Item("banana",new BigDecimal(10)));
    }
}
