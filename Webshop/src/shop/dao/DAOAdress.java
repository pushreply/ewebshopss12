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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DAOAdress {
	
	private static final Log log = LogFactory.getLog(DAOTrack.class);
    
	
	/**
	 * This Methode insert a new adress object 
	 * @param address
	 * @param db
	 */
	public void insertAddress(DBAddress address, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER insertAddress");
		}
		
		try {
			db.store(address);
		} catch (DatabaseFileLockedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("insertAddress - DB-Fehler", e);
			}
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
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER retrieveAllAdresses");
		}

		LinkedList<DBAddress> addresses = new LinkedList<DBAddress>();

		try {

			DBAddress address = new DBAddress();

			ObjectSet<DBAddress> result = db.queryByExample(address);

			while (result.hasNext()) {
				address = result.next();
				addresses.add(address);
			}
		} catch (DatabaseFileLockedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("retrieveAllAdresses - DB-Fehler", e);
			}
			
		} finally {
			db.close();
		}
		
		if(log.isDebugEnabled()){
			log.debug("LEAVE retrieveAllAdresses: Anzahl der Addresse"+addresses.size());
		}

		return addresses;
	}

	
	/**
	 * 
	 * @param adrID
	 * @param newAdress
	 * @param db
	 */
	public void updateAddress(final int adrID, DBAddress newAdress, ObjectContainer db) {
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER updateAddress"+adrID);
		}

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
			
			if (log.isErrorEnabled()) {
				log.error("updateAddress - DB-Fehler", e);
			}
		} catch (DatabaseClosedException e) {
			
			if (log.isErrorEnabled()) {
				log.error("updateAddress - DB-Fehler", e);
			}
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
		
		if (log.isInfoEnabled()) {
			log.debug("ENTER delete"+adrID);
		}

		DBAddress found = new DBAddress();

		try {

			DBCustomer address = new DBCustomer(adrID, null, null, null);
			ObjectSet<DBAddress> set = db.queryByExample(address);
			found = (DBAddress) set.next();

			db.delete(found);
            db.commit();
            
		} catch (Db4oIOException e) {
			if (log.isErrorEnabled()) {
				log.error("delete - DB-Fehler", e);
			}
			
		} catch (DatabaseClosedException e) {
			if (log.isErrorEnabled()) {
				log.error("delete - DB-Fehler", e);
			}
			
		} finally {
			db.close();
		}
	}
	
}
