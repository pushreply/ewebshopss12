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

public class DAOCustomer {

	public void insert(DBCustomer customer, ObjectContainer db) {
		try {
			db.store(customer);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

	/*
	 * Verify weather the Username allready exists in the Database
	 */
	public boolean userFound(String username, ObjectContainer db) {
		boolean found = false;
		try {

			ObjectSet<DBCustomer> customers = db
					.queryByExample(DBCustomer.class);
			for (DBCustomer customer : customers) {
				if (username.equals(customer.getUsername())) {
					found = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return found;
	}

	/*
	 * Register the user , only when this does not allready exist in the
	 * Database
	 */
	public void UserRegister(DBCustomer customer, ObjectContainer db) {

		try {

			if (userFound(customer.getUsername(), db) == false) {
				db.store(customer);

			} else {
				throw new IllegalArgumentException(
						"The user allready exists in the database!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

	}

	/*
	 * retrieve all Customers
	 */
	public LinkedList<DBCustomer> selectAllCustomer(ObjectContainer db) {

		LinkedList<DBCustomer> customers = new LinkedList<DBCustomer>();

		try {

			DBCustomer customer = new DBCustomer();

			ObjectSet<DBCustomer> result = db.queryByExample(customer);

			while (result.hasNext()) {
				customer = result.next();
				customers.add(customer);
			}
		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return customers;
	}

	/*
	 * retrieved a Custon by username, Caution: username must be unique in the
	 * database!
	 */
	public DBCustomer retrieveCustomerByID(ObjectContainer db,
			int cutID) {

		DBCustomer result = new DBCustomer();

		try {
			// Customer muss mit username eindeutig indentifizierbar sein!
			DBCustomer customer = new DBCustomer(cutID,null, null, null);
			ObjectSet<DBCustomer> set = db.queryByExample(customer);
			result = (DBCustomer) set.next();

		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return result;

	}

	
	
	/*
	 * retrieve customer adress
	 */
	public DBAddress retrieveCustomerAddress(ObjectContainer db, int cutID,
			String adressArt) { // adressArt is either delivery or billing
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
	 * retrieve customer belling adress
	 */

	public DBAddress retrieveCustomerBillingAddress(ObjectContainer db,
			int cutID) {
		return retrieveCustomerAddress(db, cutID, "billing");

	}

	/*
	 * retrieve customer delivery adress
	 */
	public DBAddress retrieveCustomerDeliveryAddress(ObjectContainer db,
			int cutID) {
		return retrieveCustomerAddress(db, cutID, "delivery");

	}

}
