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
    	TestResponse res = Utilities.request("POST","/deals?"+itemlist+"&quantity="+quantity+"&itemsFree="+itemsFree+"&dealTypes=Buy%20X%20Get%20Y%20Free")
    	Map<String, String> json = res.json();
    then:
			assert res.status == 201
			assert json.get("quantityNeeded") == quantity
      assert json.get("itemsNeeded").size() == 2
      assert json.get("itemsFree") == itemsFree
    cleanup:
      service.stopServer();
    where:
    	itemsFree    | quantity     | itemlist
      1            | 3            | "items=apple&items=banana"
  }

} 