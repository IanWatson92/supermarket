import iw.supermarket.Service
import iw.supermarket.IItem
import iw.supermarket.Item

import java.math.BigDecimal;

import spock.lang.*;

class ItemGetSpec extends spock.lang.Specification {

	// Based on example http://java.dzone.com/articles/building-simple-restful-api

  @Shared
  Service service
  
  def setupSpec() {
    service = new Service()
  }

  def "get an item"() {
    setup:
      IItem item = new Item(name, new BigDecimal(price))
      service.setItem(item)
    when:
    	TestResponse res = Utilities.request("GET","/items/"+name)
    	Map<String, String> json = res.json();
    then:
			assert res.status == 200
			assert json.get("name") == name
    where:
    	name     | price
      "Grape"  | 12
      "Carrot" | 10
  }
} 