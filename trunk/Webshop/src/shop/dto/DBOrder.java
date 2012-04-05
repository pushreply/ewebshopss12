package shop.dto;
import java.util.Date;
import java.util.LinkedList;


/**
 * @author roha0001
 *
 */

public class DBOrder {
		
		private int orderID;
		private DBCustomer cust;
		private Date datum;
		private int state;
		private DBAddress address;

		//an oder has one or many Items
		LinkedList<DBItems> Items = new LinkedList<DBItems>();
		//Adress Collection
		LinkedList<DBAddress> Idress = new LinkedList<DBAddress>();
		//Customer Collection
		LinkedList<DBCustomer> customers = new LinkedList<DBCustomer>();
		
		public DBOrder(){
			
		}
		
		public DBOrder(int orderID, DBCustomer cust, Date datum, int state,
				DBAddress address, LinkedList<DBItems> items,
				LinkedList<DBAddress> idress, LinkedList<DBCustomer> customers) {
			super();
			this.orderID = orderID;
			this.cust = cust;
			this.datum = datum;
			this.state = state;
			this.address = address;
			Items = items;
			Idress = idress;
			this.customers = customers;
		}

		public int getOrderID() {
			return orderID;
		}

		public void setOrderID(int orderID) {
			this.orderID = orderID;
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

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public DBAddress getAddress() {
			return address;
		}

		public void setAddress(DBAddress address) {
			this.address = address;
		}

		public LinkedList<DBItems> getItems() {
			return Items;
		}

		public void setItems(LinkedList<DBItems> items) {
			Items = items;
		}

		public LinkedList<DBAddress> getIdress() {
			return Idress;
		}

		public void setIdress(LinkedList<DBAddress> idress) {
			Idress = idress;
		}

		public LinkedList<DBCustomer> getCustomers() {
			return customers;
		}

		public void setCustomers(LinkedList<DBCustomer> customers) {
			this.customers = customers;
		}

		@Override
		public String toString() {
			return "DBOrder [orderID=" + orderID + ", cust=" + cust
					+ ", datum=" + datum + ", state=" + state + ", address="
					+ address + ", Items=" + Items + ", Idress=" + Idress
					+ ", customers=" + customers + "]";
		}
		
		
		
		
		
}
