import iw.supermarket.Service

import spock.lang.*

import iw.supermarket.*;

class CreateDiscountDealPostSpec extends spock.lang.Specification {

	// Based on example http://java.dzone.com/articles/building-simple-restful-api
  
  def "post a discount deal"() {
    setup:
      Service service = new Service()
      IItem item = new Item("apple", new BigDecimal(12))
      IItem item2 = new Item("banana", new BigDecimal(14))
      service.setItem(item)
      service.setItem(item2)
    when:
    	TestResponse res = Utilities.request("POST","/deals?"+itemlist+"&quantity="+quantity+"&discountPrice="+discountPrice+"&dealTypes=Buy%20X%20For%20Y")
    	Map<String, String> json = res.json();
    then:
			assert res.status == 201
			assert json.get("quantityNeeded") == quantity
      assert json.get("itemsNeeded").size() == 2
      assert json.get("discountPrice") == discountPrice
    cleanup:
      service.stopServer();
    where:
    	discountPrice | quantity     | itemlist
      1             | 3            | "items=apple&items=banana"
  }

} 