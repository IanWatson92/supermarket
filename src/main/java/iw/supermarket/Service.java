package iw.supermarket;

import static spark.Spark.*;

import java.util.Map;
import java.util.HashMap;

public class Service {

	private static Map<String, IItem> items = new HashMap<String, IItem>();

	public Service() {
		items = new HashMap<>();
		items.put("Apple",new Item("Apple"));
	}

	public static void main(String[] args) {
		new Service();
    	
		post("/items", (req, res) -> {
			String name = req.queryParams("name");
			IItem item = new Item(name);
			items.put(name,item);

			res.status(201);
			return name;
		});


    	get("/items/:name", (req, res) -> {
    		IItem item = items.get(req.params(":name"));
    		return "Name -> " + item.getName();
    	});


    }
}
