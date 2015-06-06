import iw.supermarket.Service

class ItemPostSpec extends spock.lang.Specification {

	// Based on example http://java.dzone.com/articles/building-simple-restful-api

  def "post an item and check response"() {
    when:
    	TestResponse res = Utilities.request("POST","/items?name="+name+"&price="+price)
    	Map<String, String> json = res.json();
    then:
			assert res.status == 201
			assert json.get("name") == name
      assert json.get("price") == price
    where:
    	name     | price
      "Banana" | 12
      "Orange" | 9
  }
} 