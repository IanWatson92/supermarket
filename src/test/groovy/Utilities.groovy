/*

Code taken from https://github.com/mscharhag/blog-examples/blob/master/sparkdemo/src/test/java/com/mscharhag/sparkdemo/UserControllerIntegrationTest.java

To avoid rewriting test mechanisms

*/

import com.google.gson.Gson;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class Utilities {

	public static String x () { return "x"}

	public static TestResponse request(String method, String path) throws IOException{
	
		URL url = new URL("http://localhost:4567" + path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(method);
		connection.setDoOutput(true);
		connection.connect();
		String body = IOUtils.toString(connection.getInputStream());
		return new TestResponse(connection.getResponseCode(), body);
	}
}


public class TestResponse {

	public final String body;
	public final int status;

	public TestResponse(int status, String body) {
		this.status = status;
		this.body = body;
	}

	public Map<String,String> json() {
		return new Gson().fromJson(body, HashMap.class);
	}
}