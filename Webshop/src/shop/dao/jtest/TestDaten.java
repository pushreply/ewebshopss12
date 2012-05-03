package shop.dao.jtest;

import java.io.File;
import java.io.IOException;
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
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.Db4oIOException;

import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.NoMP3FrameException;

/**
 * 
 * @author mukunzi
 * @author Andreas
 * 
 */

public class TestDaten {

	public static void main(String[] arg) throws ID3v2WrongCRCException, ID3v2DecompressionException, ID3v2IllegalVersionException, IOException, NoMP3FrameException, Db4oIOException, DatabaseClosedException, InstantiationException, IllegalAccessException {

		//open database connection
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
		String[] paths = { "WebContent/images/wwm.mp3",
				"WebContent/images/wwm.mp3", "WebContent/images/wwm.mp3" };
		for (String path : paths) {
			daoTrack.create(Trackfactory.createTrack(new File(path)));
		}

		// Categories
		String[] categories = { "Rock", "Pop", "HipHop", "Rock'n'Roll", "Rap",
				"Romance", "Opera", "Schlager", "Metal" };
		for (String categorie : categories) {
			daoCategory.create(new DBCategory(categorie));
		}

		// Keywords
		String[] keywords = { "Geniale Scheibe", "Radio-Mitschnitt",
				"Ohrenkrebsgefahr", "Ohrwurm", "Evergreen", "One Hit Wonder" };
		for (String keyword : keywords) {
			daoKeyword.create(new DBKeyword(keyword));
		}

		// Albums
		//	load existing keywords, categories and tracks to put in the albums
		List<DBKeyword> dbKeys = daoKeyword.readAll();
		List<DBCategory> dbCats = daoCategory.readAll();
		List<DBTrack> dbTracks = daoTrack.readAll();

		//pick random keywords, categories & tracks and adds them to album
		for (int i = 0; i < 5; i++) {
			List<DBKeyword> albumKeywords = new LinkedList<DBKeyword>();
			for (DBKeyword dbKeyword : dbKeys) {
				if (Math.random() < 0.5)
					albumKeywords.add(dbKeyword);
			}

			List<DBCategory> albumCategories = new LinkedList<DBCategory>();
			for (DBCategory dbCategory : dbCats) {
				if (Math.random() < 0.5)
					albumCategories.add(dbCategory);
			}

			List<DBTrack> albumTracks = new LinkedList<DBTrack>();
			//ensures that at least one song is in the album
			albumTracks.add(dbTracks.get(0));
			
			for (DBTrack dbTrack : dbTracks) {
				if (Math.random() < 0.5)
					albumTracks.add(dbTrack);
			}

			daoAlbum.create(new DBAlbum(null, i + ". Album Title", "Artist_" + i + "_"+ i,
					(int) Math.random() * 3, Math.random() * 20, (int) Math
							.random() * 500, (int) albumTracks.size(), "Label_"
							+ i + " "+ i+1 + " "+ i+2, albumKeywords, albumCategories, albumTracks));
		}
		db.close();
		System.out.println("Testdaten wurden geschrieben");
	}
}
