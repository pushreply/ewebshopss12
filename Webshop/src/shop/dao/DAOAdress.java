package shop.dao;

/**
 * @author mukunzi
 */

import java.util.LinkedList;

import shop.dto.DBAddress;
import shop.dto.DBCustomer;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.Db4oIOException;

public class DAOAdress {
    
	
	/**
	 * This Methode insert a new adress object 
	 * @param address
	 * @param db
	 */
	public void insert(DBAddress address, ObjectContainer db) {
		try {
			db.store(address);
		} catch (DatabaseFileLockedException e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
    
	
	/**
	 * 
	 * @param db
	 * @return
	 */
	public LinkedList<DBAddress> retrieveAllAdresses(ObjectContainer db) {

		LinkedList<DBAddress> addresses = new LinkedList<DBAddress>();

		try {

			DBAddress address = new DBAddress();

			ObjectSet<DBAddress> result = db.queryByExample(address);

			while (result.hasNext()) {
				address = result.next();
				addresses.add(address);
			}
		} catch (DatabaseFileLockedException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			db.close();
		}

		return addresses;
	}

	
	/**
	 * 
	 * @param adrID
	 * @param newAdress
	 * @param db
	 */
	public void update(final int adrID, DBAddress newAdress, ObjectContainer db) {

		DBAddress found = new DBAddress();

		try {

			DBCustomer address = new DBCustomer(adrID, null, null, null);
			ObjectSet<DBAddress> set = db.queryByExample(address);
			found = (DBAddress) set.next();
			found.setStreet(newAdress.getStreet());
			found.setCountry(newAdress.getCountry());
			found.setFirstName(newAdress.getFirstName());
			found.setGender(newAdress.getGender());
			found.setArt(newAdress.getArt());

			db.store(found);

		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
	
	/**
	 * 
	 * @param adrID
	 * @param db
	 */
	public void delete(final int adrID,ObjectContainer db) {

		DBAddress found = new DBAddress();

		try {

			DBCustomer address = new DBCustomer(adrID, null, null, null);
			ObjectSet<DBAddress> set = db.queryByExample(address);
			found = (DBAddress) set.next();

			db.delete(found);
            db.commit();
            
		} catch (Db4oIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.close();
		}
	}
	
}
