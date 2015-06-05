import iw.supermarket.Service
import iw.supermarket.IItem
import iw.supermarket.Item

class ItemGetSpec extends spock.lang.Specification {

	// Based on example http://java.dzone.com/articles/building-simple-restful-api

	def setupSpec() {
		Service.main(null)
	}


  def "get an item"() {
    setup:
      IItem item = new Item(name)
      Service service = new Service()
      service.setItem(item)

    when:
    	TestResponse res = Utilities.request("GET","/items/"+name)
    	Map<String, String> json = res.json();
    then:
			assert res.status == 200
			assert json.get("name") == name
    where:
    	name << ["Grape", "Carrot"]
  }
} 