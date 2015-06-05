import iw.supermarket.Service

class ItemPostSpec extends spock.lang.Specification {

	// Based on example http://java.dzone.com/articles/building-simple-restful-api

	def setupSpec() {
		Service.main(null)
	}


  def "post an item and check response"() {
    when:
    	TestResponse res = Utilities.request("POST","/items?name="+name)
    	Map<String, String> json = res.json();
    then:
			assert res.status == 201
			assert json.get("name") == name
    where:
    	name << ["Banana", "Orange"]
  }
} 