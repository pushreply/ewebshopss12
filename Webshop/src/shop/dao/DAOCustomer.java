package shop.dao;

/**
 * @author mukunzi
 */

import java.util.LinkedList;

import com.db4o.ObjectSet;
import com.db4o.ObjectContainer;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;

import shop.dto.DBAddress;
import shop.dto.DBCustomer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DAOCustomer {
	
	private static final Log log = LogFactory.getLog(DAOTrack.class);

	public static void insert(DBCustomer customer, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER insert");
		}
		
		try {
			db.store(customer);
		} catch (DatabaseFileLockedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("insert - DB-Fehler", e);
			}
			
		} finally {
			db.close();
			if(log.isDebugEnabled()){
				log.debug("LEAVE insert");
			}
		}
	}

	/*
	 * Verify weather the Username allready exists in the Database
	 */
	public static boolean userFound(String username, String password, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER userFound");
		}
		
		boolean found = false;
		try {

			ObjectSet<DBCustomer> customers = db
					.queryByExample(DBCustomer.class);
			for (DBCustomer customer : customers) {
				if (username.equals(customer.getUsername())&& username.equals(customer.getPassword())) {
					found = true;
					break;
				}
			}

		} catch (Exception e) {
			
			if (log.isErrorEnabled()) {
				log.error("userFound - DB-Fehler", e);
			}
			
		} finally {
			db.close();
		}
        
		if(log.isDebugEnabled()){
			log.debug("LEAVE userFound"+found);
		}
		
		return found;
	}

	/*
	 * Register the user , only when this does not allready exist in the
	 * Database
	 */
	public static void registerUser(DBCustomer customer, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER registerUser");
		}

		try {

			if (userFound(customer.getUsername(), customer.getPassword(), db) == false) {
				db.store(customer);

			} else {
				throw new IllegalArgumentException(
						"The user allready exists in the database!");
			}

		} catch (Exception e) {
			
			if (log.isErrorEnabled()) {
				log.error("registerUser - DB-Fehler", e);
			}
			
		} finally {
			db.close();
			
			if(log.isDebugEnabled()){
				log.debug("LEAVE registerUser");
			}
			
		}

	}

	/*
	 * retrieve all Customers
	 */
	public static LinkedList<DBCustomer> selectAllCustomer(ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER selectAllCustomer");
		}

		LinkedList<DBCustomer> customers = new LinkedList<DBCustomer>();

		try {

			DBCustomer customer = new DBCustomer();

			ObjectSet<DBCustomer> result = db.queryByExample(customer);

			while (result.hasNext()) {
				customer = result.next();
				customers.add(customer);
			}
		} catch (DatabaseFileLockedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("selectAllCustomer - DB-Fehler", e);
			}
			
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE selectAllCustomer"+customers.size());
		}

		return customers;
	}

	/*
	 * retrieved a Custon by username, Caution: username must be unique in the
	 * database!
	 */
	public static DBCustomer retrieveCustomerByID(ObjectContainer db,
			int cutID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveCustomerByID"+cutID);
		}

		DBCustomer result = new DBCustomer();

		try {
			// Customer muss mit username eindeutig indentifizierbar sein!
			DBCustomer customer = new DBCustomer(cutID,null, null, null);
			ObjectSet<DBCustomer> set = db.queryByExample(customer);
			result = (DBCustomer) set.next();

		} catch (DatabaseFileLockedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("retrieveCustomerByID - DB-Fehler", e);
			}
			
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveCustomerByID");
		}

		return result;

	}

	
	
	/*
	 * retrieve customer adress
	 */
	public static DBAddress retrieveCustomerAddress(ObjectContainer db, int cutID,
			String adressArt) { // adressArt is either delivery or billing
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveCustomerAddress");
		}
			
		DBAddress address = null;

		try {
			ObjectSet<DBCustomer> result = db.queryByExample(new DBCustomer(cutID,
					null, null, null));
			DBCustomer customer = (DBCustomer) result.next();
			LinkedList<DBAddress> adresses = customer.getAddresses();

			for (DBAddress add : adresses) {
				if (add.getArt() == adressArt) {
					address = new DBAddress(add.getAdrID(), add.getStreet(),
							add.getCountry(), add.getFirstName(),
							add.getLastName(), add.getGender(), add.getArt());
				}
			}
		} catch (Db4oIOException e) {
			
			if (log.isErrorEnabled()) {
				log.error("retrieveCustomerAddress - DB-Fehler", e);
			}
			
		} catch (DatabaseClosedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("retrieveCustomerAddress - DB-Fehler", e);
			}
			
		}finally{
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveCustomerAddress");
		}
		
		return address;
	}

	/*
	 * retrieve customer belling adress
	 */

	public static DBAddress retrieveCustomerBillingAddress(ObjectContainer db,
			int cutID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveCustomerBillingAddress");
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveCustomerBillingAddress");
		}
		
		return retrieveCustomerAddress(db, cutID, "billing");
		
		

	}

	/*
	 * retrieve customer delivery adress
	 */
	public static DBAddress retrieveCustomerDeliveryAddress(ObjectContainer db,
			int cutID) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveCustomerDeliveryAddress");
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveCustomerDeliveryAddress");
		}
		
		return retrieveCustomerAddress(db, cutID, "delivery");

	}

}
