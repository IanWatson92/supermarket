import iw.supermarket.Service

import spock.lang.*

import iw.supermarket.*;

class CreateDealPostSpec extends spock.lang.Specification {

	// Based on example http://java.dzone.com/articles/building-simple-restful-api
  
  def "post a deal"() {
    setup:
      Service service = new Service()
      IItem item = new Item("apple", new BigDecimal(12))
      IItem item2 = new Item("banana", new BigDecimal(14))
      service.setItem(item)
      service.setItem(item2)
    when:
    	TestResponse res = Utilities.request("POST","/deals?"+itemlist+"&quantity="+quantity)
    	Map<String, String> json = res.json();
    then:
			assert res.status == 201
			assert json.get("quantityNeeded") == quantity
      assert json.get("itemsNeeded").size() == 2
    cleanup:
      service.stopServer();
    where:
    	quantity     | itemlist
      3            | "items=apple&items=banana"
  }

} 