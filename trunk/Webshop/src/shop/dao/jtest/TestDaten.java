package shop.dao.jtest;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import shop.dao.DBObject;
import shop.dao.GenericDaoImpl;
import shop.dao.IGenericDao;
import shop.dto.DBAddress;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBKeyword;
import shop.dto.DBTrack;
import shop.util.Trackfactory;

import com.db4o.ObjectContainer;

/**
 * 
 * @author mukunzi
 * @author Andreas
 * 
 */

public class TestDaten {

	public static void main(String[] arg) {

		// open database connection
		ObjectContainer db = new DBObject().getConnection();

		// initialize DAOs
		IGenericDao<DBAddress> doaAddress = new GenericDaoImpl<DBAddress>(
				DBAddress.class, db);
		IGenericDao<DBCategory> daoCategory = new GenericDaoImpl<DBCategory>(
				DBCategory.class, db);
		IGenericDao<DBCustomer> daoCustomer = new GenericDaoImpl<DBCustomer>(
				DBCustomer.class, db);
		IGenericDao<DBKeyword> daoKeyword = new GenericDaoImpl<DBKeyword>(
				DBKeyword.class, db);
		IGenericDao<DBTrack> daoTrack = new GenericDaoImpl<DBTrack>(
				DBTrack.class, db);
		IGenericDao<DBAlbum> daoAlbum = new GenericDaoImpl<DBAlbum>(
				DBAlbum.class, db);

		// DBAddres-Objekts
		List<DBAddress> adresses = new LinkedList<DBAddress>();
		adresses.add(new DBAddress("Schillerplatz 30", "Deutschland", "Jens",
				" Müller", "M", "delivery"));
		adresses.add(new DBAddress("Schillerplatz 30", "Deutschland", "Jens",
				" Müller", "M", "billing"));
		adresses.add(new DBAddress("Amerikastrasse 1", "Deutschland", "Kevin",
				" Schneider", "M", "delivery"));
		adresses.add(new DBAddress("Pfaffplatz 10", "Deutschland", "kevin",
				" Schneider", "M", "belling"));
		adresses.add(new DBAddress("Polizeipresidium 14", "Deutschland",
				"Risa", " Stefanie", "W", "delivery"));
		adresses.add(new DBAddress("Poststr. 12", "Deutschland", "Risa",
				" Stefanie", "M", "billing"));
		adresses.add(new DBAddress("Kurt-Schumacherstr. 22", "Deutschland",
				"Miriam", " Cole", "W", "delivery"));
		adresses.add(new DBAddress("Kurt-Schumacherstr. 22", "Deutschland",
				"Miriam", " Cole", "W", "billing"));
		adresses.add(new DBAddress("Gerhart-HAuptmannstr.17", "Deutschland",
				"Octavius", "Robert", "M", "delivery"));
		adresses.add(new DBAddress("Gerhart-HAuptmannstr.17", "Deutschland",
				"Octavius", "Robert", "M", "billing"));

		for (DBAddress dbAddress : adresses) {
			doaAddress.create(dbAddress);
		}

		// DBCustomer-Objekts
		LinkedList<DBAddress> addresses1 = new LinkedList<DBAddress>();
		addresses1.add(adresses.get(0));
		addresses1.add(adresses.get(1));
		DBCustomer customer1 = new DBCustomer("Admin", "Admin", addresses1);
		daoCustomer.create(customer1);

		LinkedList<DBAddress> addresses2 = new LinkedList<DBAddress>();
		addresses2.add(adresses.get(2));
		addresses2.add(adresses.get(3));
		DBCustomer customer2 = new DBCustomer("Kev", "schkev", addresses2);
		daoCustomer.create(customer2);

		LinkedList<DBAddress> addresses3 = new LinkedList<DBAddress>();
		addresses3.add(adresses.get(4));
		addresses3.add(adresses.get(5));
		DBCustomer customer3 = new DBCustomer("Risa2", "Rist254", addresses3);
		daoCustomer.create(customer3);

		LinkedList<DBAddress> addresses4 = new LinkedList<DBAddress>();
		addresses4.add(adresses.get(6));
		addresses4.add(adresses.get(7));
		DBCustomer customer4 = new DBCustomer("Mir45", "ret234", addresses4);
		daoCustomer.create(customer4);

		LinkedList<DBAddress> addresses5 = new LinkedList<DBAddress>();
		addresses5.add(adresses.get(8));
		addresses5.add(adresses.get(9));
		DBCustomer customer5 = new DBCustomer("oct234", "weru5", addresses5);
		daoCustomer.create(customer5);

		// Track
//		String path = "";
//		for (int i = 1; i <= 10; i++) {
//			// set path for next iteration
//			path = "WebContent/images/Alb1_Disc" + (i <= 5 ? "1" : "2")
//					+ "_Track" + i + ".mp3";
//			try {
//				daoTrack.create(Trackfactory.createTrack(new File(path)));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}

		// Categories
		String[] categories = { "Rock", "Pop", "HipHop", "Rock'n'Roll", "Rap",
				"Romance", "Opera", "Schlager", "Metal" };
		for (String categorie : categories) {
			daoCategory.create(new DBCategory(categorie));
		}

		// Keywords
		String[] keywords = { "Geniale Scheibe", "Radio-Mitschnitt",
				"Ohrenkrebsgefahr", "Ohrwurm", "Evergreen", "One Hit Wonder",
				"Klasse", "Sommer 2012", "Unsere Empfehlung" };
		for (String keyword : keywords) {
			daoKeyword.create(new DBKeyword(keyword));
		}

		// Albums
		// load existing keywords, categories and tracks to put in the albums
//		List<DBKeyword> allKeywords = null;
//		List<DBCategory> allCategories = null;
//		List<DBTrack> allTracks = null;
//		try {
//			allKeywords = daoKeyword.readAll();
//			allCategories = daoCategory.readAll();
//			allTracks = daoTrack.readAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// pick random keywords, categories & tracks and adds them to album
//		for (int i = 0; i < 5; i++) {
//			List<DBKeyword> albumKeywords = addRandom(allKeywords);
//			List<DBCategory> albumCategories = addRandom(allCategories);
//			List<DBTrack> albumTracks = addRandom(allTracks);
//
//			daoAlbum.create(new DBAlbum(null, i + ". Album Title", "Artist_"
//					+ i + "_" + i, (int) (Math.random() * 2+1), (int) (Math.random() * 20)+1,
//					(int) (Math.random() * 500)+1, (int) albumTracks.size(),
//					"Label_" + i + " " + i + 1 + " " + i + 2, albumKeywords,
//					albumCategories, albumTracks));
//		}
		db.close();
		System.out.println("Testdaten wurden geschrieben");
	}

	/**
	 * @author Andreas
	 * @param allItems
	 * @return
	 */
	private static <T> List<T> addRandom(List<T> allItems) {
		List<T> tempItemsCopy = allItems;
		List<T> albumItem = new LinkedList<T>();
		// ensures that at least one item is in the album
		albumItem.add(tempItemsCopy.get(0));
		tempItemsCopy.remove(0);
		// pick random item
		for (T item : tempItemsCopy) {
			if (Math.random() < 0.5)
				albumItem.add(item);
		}
		return albumItem;
	}
}
