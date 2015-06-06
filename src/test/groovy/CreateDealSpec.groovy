import iw.supermarket.Service
import iw.supermarket.IDeal
import iw.supermarket.Deal

import iw.supermarket.IItem
import iw.supermarket.Item

import java.math.BigDecimal;

import spock.lang.*;

class CreateDealSpec extends spock.lang.Specification {

/*
Can't see a easy way to use data driven approach for multiple items, will hardcode for now
*/
  def "create a deal"() {
    setup:
      IItem item = new Item("Grape", 12, 2.25)
      Set<IItem> items = new HashSet<IItem>();
      items.add(item)
      IDeal deal = new Deal(1,items,3,1)
    when:
    	Set<IItem> itemsNeeded = deal.getItemsNeeded();
    then:
			assert itemsNeeded.size() == 1
			assert deal.quantityNeeded == 3
      assert deal.itemsFree == 1
  }

} 