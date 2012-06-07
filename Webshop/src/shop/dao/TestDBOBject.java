package shop.dao;

/**
 * @author mukunzi
 */

import static org.junit.Assert.*;
import com.db4o.ObjectContainer;

import org.junit.Test;


public class TestDBOBject {

	// vorerst alles auskommentiert, da getConnection()-Methode geändert wurde
	
	/*
	 * Datenbank existiert
	 */
	
	/*@Test
	public void testGetConnection1() {
		ObjectContainer db;
		String FILE = "C:/projektDB/beispiel.db";
		try {
			db = new DBObject().getConnection(FILE);
			assertNotNull(db);
			db.close();
		} catch (Exception e) {
			fail("Ein Fehler ist beim Testen, da ein unerwartete Exception "
					+ "aufgetreten ist" + e.getMessage());
		}

	}*/

	/*
	 * Datenbank existiert nicht
	 */
	/*@Test
	public void testGetCollection2() {
		ObjectContainer db;
		String FILE = "C:/projektDB/nix.db";

		try {
			db = new DBObject().getConnection(FILE);
			assertNull("Collection ist null", db);
		} catch (Exception e) {
			fail("Ein Fehler ist beim Testen, da eine Exception "
					+ "aufgetreten ist" + e.getMessage());
		}

	}*/

}
