package iw.supermarket;

import static spark.Spark.*;

import java.util.Map;
import java.util.HashMap;

public class Service {

	private static Map<String, IItem> items = new HashMap<String, IItem>();

	public Service() {

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
	}

	public IItem getItem(String name) {
		return items.get(name);
	}

	public void setItem(IItem item) {
		items.put(item.getName(),item);
	}

	public static void main(String[] args) {
		Service service = new Service();
    }
}
