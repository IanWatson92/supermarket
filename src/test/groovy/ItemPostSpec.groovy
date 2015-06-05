import iw.supermarket.Service

class ItemPostSpec extends spock.lang.Specification {

	def setupSpec() {
		Service.main(null)
	}


  def "post an item and check response"() {
    when:
    	TestResponse res = Utilities.request("POST","/items?name="+name)
    then:
			assert res.status == 201
    where:
    	name << ["Banana"]
  }
} 