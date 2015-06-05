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

public class Service {

	private Logger _logger;
	private Items items;

	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

	public Service() {
		items = new Items();
		_logger = Logger.getLogger(this.getClass().getName());

		try {
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("resourcesPath")));
		} catch (IOException e) {
			_logger.log(Level.SEVERE,"Exception thrown", e);
		}

		post("/items", "application/json", (req, res) -> {
			String name = req.queryParams("name");
			IItem item = new Item(name);
			items.setItem(item);

			res.status(201);
			return item;
		}, new JsonTransformer());


    	get("/items/:name", "application/json", (req, res) -> {
    		String name = req.params(":name");
    		return items.getItem(name);
    	}, new JsonTransformer());

    	get("/items", "application/json", (req, res) -> {
    		return items.getItems();
    	}, new JsonTransformer());

    	get("/", (req, res) -> {
    		Map<String,Object> attributes = new HashMap<>();
    		attributes.put("message","Hello!");
    		attributes.put("items",items.getItems());
    		return new ModelAndView(attributes,"home.ftl");
    	}, new FreeMarkerEngine(cfg));
	}

	public static void main(String[] args) {
		Service service = new Service();
		service.items.setItem(new Item("apple"));
		service.items.setItem(new Item("banana"));
    }
}
