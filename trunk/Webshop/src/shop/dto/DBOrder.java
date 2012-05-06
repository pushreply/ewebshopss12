package shop.dto;
import java.util.Date;
import java.util.LinkedList;


/**
 * @author roha0001
 * @author mukunzi
 *
 */

public class DBOrder extends DBUUIDBase {
		
		private Date datum;
		private int state;

		//an oder has one or many Items
		LinkedList<DBItems> items = new LinkedList<DBItems>();
		//Adress Collection
		LinkedList<DBAddress> addresses = new LinkedList<DBAddress>();
		//Customer Collection
		LinkedList<DBCustomer> customers = new LinkedList<DBCustomer>();
		
		public DBOrder(){
			
		}

		public DBOrder(Date datum, int state,
				LinkedList<DBItems> items, LinkedList<DBAddress> addresses,
				LinkedList<DBCustomer> customers) {
			super();
			this.datum = datum;
			this.state = state;
			this.items = items;
			this.addresses = addresses;
			this.customers = customers;
		}

		public Date getDatum() {
			return datum;
		}

		public void setDatum(Date datum) {
			this.datum = datum;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public LinkedList<DBItems> getItems() {
			return items;
		}

		public void setItems(LinkedList<DBItems> items) {
			this.items = items;
		}

		public LinkedList<DBAddress> getAddresses() {
			return addresses;
		}

		public void setAddresses(LinkedList<DBAddress> addresses) {
			this.addresses = addresses;
		}

		public LinkedList<DBCustomer> getCustomers() {
			return customers;
		}

		public void setCustomers(LinkedList<DBCustomer> customers) {
			this.customers = customers;
		}

		@Override
		public String toString() {
			return "DBOrder [datum=" + datum
					+ ", state=" + state + ", items=" + items + ", addresses="
					+ addresses + ", customers=" + customers + "]";
		}
		
	    
		
}
