package shop.dto;
import java.util.Date;
import java.util.LinkedList;


/**
 * @author roha0001
 *
 */

public class DBOrder {

	public class Order {
		
		private DBCustomer cust;
		private Date datum;
		private DBItems item;
		private int state;

		//An order can have more than one item.
		LinkedList<DBItems> orderedItem = new LinkedList<DBItems>();
		
		public LinkedList<DBItems> getOrderedItem() {
			return orderedItem;
		}
		public void setOrderedItem(LinkedList<DBItems> orderedItem) {
			this.orderedItem = orderedItem;
		}
		public Order(DBCustomer cust, Date datum, DBItems item, int state) {
			super();
			this.cust = cust;
			this.datum = datum;
			this.item = item;
			this.state = state;
		}
		public DBCustomer getCust() {
			return cust;
		}
		public void setCust(DBCustomer cust) {
			this.cust = cust;
		}
		public Date getDatum() {
			return datum;
		}
		public void setDatum(Date datum) {
			this.datum = datum;
		}
		public DBItems getItem() {
			return item;
		}
		public void setItem(DBItems item) {
			this.item = item;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		@Override
		//maybe useful for logging.
		public String toString() {
			return "Order [cust=" + cust + ", datum=" + datum + ", item="
					+ item + "]";
		}
	}
		
}
