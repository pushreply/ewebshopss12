package shop.dao;

/**
 * @author mukunzi
 */

import java.util.LinkedList;

import shop.dto.DBAddress;
import shop.dto.DBCustomer;
import shop.dto.DBItems;
import shop.dto.DBOrder;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;

public class DAOOrder {
	

	/*
	 * insert a new order in the Database
	 */
	public static void insertOrder(DBOrder order, ObjectContainer db) {
		try {
			db.store(order);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	
    /*
     * delete an order by orderID
     */
	public static void deleteOrder(int orderID, ObjectContainer db) {
		DBOrder order;
		try {

			ObjectSet<DBOrder> result = 
					db.queryByExample(new DBOrder(orderID, null, 0, null, null, null));
			order = result.next();

			db.delete(order);
			db.commit();
			
		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

	}
	
	

	/*
	 * retrieve all orders
	 */
	public static LinkedList<DBOrder> retieveAllOders(ObjectContainer db) {

		LinkedList<DBOrder> orders = new LinkedList<DBOrder>();
		DBOrder order = new DBOrder();

		try {

			ObjectSet<DBOrder> result = db.queryByExample(DBOrder.class);

			while (result.hasNext()) {
				order = result.next();
				orders.add(order);
			}
		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return orders;
	}
	

	/*
	 * Retrieve adress (biling or delivery) of an order
	 */
	public static DBAddress retieveOrderAdress(ObjectContainer db, int orderID,
			String adressArt) { // adressArt is either billing or delivery
		DBOrder result;
		DBAddress address = new DBAddress();
		try {
			DBOrder order = new DBOrder(orderID, null, 0, null, null, null);
			ObjectSet<DBOrder> set = db.queryByExample(order);
			result = (DBOrder) set.next();
			LinkedList<DBAddress> addresses = result.getAddresses();

			for (DBAddress ord : addresses) {

				if (ord.getArt() == adressArt) {
					address = ord;
				}

			}
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.close();
		}

		return address;

	}
	
	

	/*
	 * Retrieve delivery adress of an order
	 */
	public static DBAddress retieveOrderDeliveryAdress(ObjectContainer db, int orderID) {

		return retieveOrderAdress(db, orderID, "delivery");
	}
	
	

	/*
	 * Retrieve billing adress of an order
	 */
	public static DBAddress retieveOrderBillingAdress(ObjectContainer db, int orderID) {
		return retieveOrderAdress(db, orderID, "belling");
	}
	
	

	/*
	 * Retrieve all Customer of an Order
	 */
	public static LinkedList<DBCustomer> retieveOrderCustomers(ObjectContainer db,
			int orderID) {

		DBOrder result;
		LinkedList<DBCustomer> customers = null;

		try {
			DBOrder order = new DBOrder(orderID, null, 0, null, null, null);
			ObjectSet<DBOrder> set = db.queryByExample(order);
			result = (DBOrder) set.next();
			customers = result.getCustomers();

		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customers;

	}
	
	

	/*
	 * retrieve all items of an order
	 */
	public static LinkedList<DBItems> retieveOrderItems(ObjectContainer db, int orderID) {

		DBOrder result;
		LinkedList<DBItems> items = null;

		try {
			DBOrder order = new DBOrder(orderID, null, 0, null, null, null);
			ObjectSet<DBOrder> set = db.queryByExample(order);
			result = (DBOrder) set.next();
			items = result.getItems();

		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return items;

	}
	
	

	/*
	 * retrieve all customer's orders
	 */
	public static LinkedList<DBOrder> retrieveCustomerOrder(ObjectContainer db, int custID) {
		
        LinkedList<DBOrder> orders = new LinkedList<DBOrder>();
        
		try {
			ObjectSet<DBOrder> result = db.queryByExample(DBOrder.class);
			
			for (DBOrder order : result) {
				LinkedList<DBCustomer> customers;
				customers = order.getCustomers();
				
				for(DBCustomer customer : customers){
					if(customer.getCustID() == custID){
				    orders.add(order);
				    break;
					}
				}
				
				}
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return orders;
		}
	}


