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

public class Deals {

	private static Map<Integer, IDeal> deals = new HashMap<Integer, IDeal>();
	private Logger _logger;

	public Deals() {
		_logger = Logger.getLogger(this.getClass().getName());
	}

	public IDeal getDeal(Integer id) {
		IDeal deal = deals.get(id);
		return deal;
	}

	public Map<Integer,IDeal> getDeals() {
		return deals;
	}

	public void setDeal(IDeal deal) {
		deals.put(deal.getDealId(),deal);
	}

}
