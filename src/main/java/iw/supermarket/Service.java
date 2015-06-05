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
	private static Map<String, IItem> items = new HashMap<String, IItem>();

	private Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

	public Service() {
		_logger = Logger.getLogger(this.getClass().getName());

		try {
			cfg.setDirectoryForTemplateLoading(new File(System.getProperty("resourcesPath")));
		} catch (IOException e) {
			_logger.log(Level.SEVERE,"Exception thrown", e);
		}

		post("/items", "application/json", (req, res) -> {
			String name = req.queryParams("name");
			IItem item = new Item(name);
			setItem(item);

			res.status(201);
			return item;
		}, new JsonTransformer());


    	get("/items/:name", "application/json", (req, res) -> {
    		String name = req.params(":name");
    		return getItem(name);
    	}, new JsonTransformer());

    	get("/", (req, res) -> {
    		Map<String,Object> attributes = new HashMap<>();
    		attributes.put("message","Hello!");
    		return new ModelAndView(attributes,"home.ftl");
    	}, new FreeMarkerEngine(cfg));
	}

	public IItem getItem(String name) {
		_logger.log(Level.INFO,"GetItem invoked with " + name);
		_logger.log(Level.INFO,"Items size is " + items.size());
		IItem item = items.get(name);
		_logger.log(Level.INFO,"Item is " + item);
		return items.get(name);
	}

	public void setItem(IItem item) {
		_logger.log(Level.INFO,"SetItem invoked with " + item.getName());
		items.put(item.getName(),item);
		_logger.log(Level.INFO,"Items size now is " + items.size());
	}

	public static void main(String[] args) {
		Service service = new Service();
    }
}
