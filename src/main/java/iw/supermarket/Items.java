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

public class Items {

	private static Map<String, IItem> items = new HashMap<String, IItem>();
	private Logger _logger;

	public Items() {
		_logger = Logger.getLogger(this.getClass().getName());
	}

	public IItem getItem(String name) {
		_logger.log(Level.INFO,"GetItem invoked with " + name);
		_logger.log(Level.INFO,"Items size is " + items.size());

		_logger.log(Level.INFO,"Items Map is " + items.toString());
		IItem item = items.get(name);
		_logger.log(Level.INFO,"Item is " + item);
		return item;
	}

	public Map<String,IItem> getItems() {
		return items;
	}

	public void setItem(IItem item) {
		_logger.log(Level.INFO,"SetItem invoked with " + item.getName());
		items.put(item.getName(),item);
		_logger.log(Level.INFO,"Items size now is " + items.size());
	}

}
