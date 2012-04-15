package shop.dao.jtest;

import java.io.File;
import java.util.LinkedList;

import shop.dto.DBAddress;
import shop.dto.DBAlbum;
import shop.dto.DBCategory;
import shop.dto.DBCustomer;
import shop.dto.DBKeyword;
import shop.dto.DBTrack;
import shop.util.Trackfactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class TestDaten {
	
	public static void main(String[] arg){
		
		// Öffne eine Datenbank
		  ObjectContainer db = Db4oEmbedded.openFile("C:\\projektDB\\beispiel.db");
		  try { 
			  
	 // DBAddres-Objekte
			 DBAddress adress1 = new DBAddress(1,"Schillerplatz 30","Deutschland","Jens"," Müller","M","delivery");
			 DBAddress adress2 = new DBAddress(2,"Schillerplatz 30","Deutschland","Jens"," Müller","M","billing");
			 
			 DBAddress adress3 = new DBAddress(3,"Amerikastrasse 1","Deutschland","Kevin"," Schneider","M","delivery");
			 DBAddress adress4 = new DBAddress(4,"Pfaffplatz 10","Deutschland","kevin"," Schneider","M","belling");
			 
			 DBAddress adress5 = new DBAddress(5,"Polizeipresidium 14","Deutschland","Risa"," Stefanie","W","delivery");
			 DBAddress adress6 = new DBAddress(6,"Poststr. 12","Deutschland","Risa"," Stefanie","M","billing");
			 
			 DBAddress adress7 = new DBAddress(7,"Kurt-Schumacherstr. 22","Deutschland","Miriam"," Cole","W","delivery");
			 DBAddress adress8 = new DBAddress(8,"Kurt-Schumacherstr. 22","Deutschland","Miriam"," Cole","W","billing");
			 
			 DBAddress adress9 = new DBAddress(9,"Gerhart-HAuptmannstr.17","Deutschland","Octavius","Robert","M","delivery");
			 DBAddress adress10 = new DBAddress(10,"Gerhart-HAuptmannstr.17","Deutschland","Octavius","Robert","M","billing");
			 
		//DBCustomer-Objekte
			 LinkedList<DBAddress> addresses1 = new LinkedList<DBAddress>();
			 addresses1.add(adress1);
			 addresses1.add(adress2);
			 DBCustomer customer1 = new DBCustomer(1,"Jemu","mu123",addresses1);
			 db.store(customer1);
			 
			 LinkedList<DBAddress> addresses2 = new LinkedList<DBAddress>();
			 addresses1.add(adress3);
			 addresses1.add(adress4);
			 DBCustomer customer2 = new DBCustomer(1,"Kev","schkev",addresses2);
			 db.store(customer2);
			 
			 LinkedList<DBAddress> addresses3 = new LinkedList<DBAddress>();
			 addresses1.add(adress5);
			 addresses1.add(adress6);
			 DBCustomer customer3 = new DBCustomer(1,"Risa2","Rist254",addresses3);
			 db.store(customer3);
			 
			 LinkedList<DBAddress> addresses4 = new LinkedList<DBAddress>();
			 addresses1.add(adress7);
			 addresses1.add(adress8);
			 DBCustomer customer4 = new DBCustomer(1,"Mir45","ret234",addresses4);
			 db.store(customer4);
			 
			 LinkedList<DBAddress> addresses5 = new LinkedList<DBAddress>();
			 addresses1.add(adress9);
			 addresses1.add(adress10);
			 DBCustomer customer5 = new DBCustomer(1,"oct234","weru5",addresses5);
			 db.store(customer5);
			 
	//Track
			 File file1 = new File("WebContent/images/wwm.mp3");
			 DBTrack track1 = Trackfactory.createTrack(file1,1);
			 
			 File file2 = new File("WebContent/images/wwm.mp3");
			 DBTrack track2 = Trackfactory.createTrack(file2,2);
			 
			 File file3 = new File("WebContent/images/wwm.mp3");
			 DBTrack track3 = Trackfactory.createTrack(file3,3);
			 
			 File file4 = new File("WebContent/images/wwm.mp3");
			 DBTrack track4 = Trackfactory.createTrack(file4,4);
			 
			 File file5 = new File("WebContent/images/wwm.mp3");
			 DBTrack track5 = Trackfactory.createTrack(file5,5);
			 
			 File file6 = new File("WebContent/images/wwm.mp3");
			 DBTrack track6 = Trackfactory.createTrack(file6,6);
			 
			 File file7 = new File("WebContent/images/wwm.mp3");
			 DBTrack track7 = Trackfactory.createTrack(file7,7);
			 
			 File file8 = new File("WebContent/images/wwm.mp3");
			 DBTrack track8 = Trackfactory.createTrack(file8,8);
			 
			 File file9 = new File("WebContent/images/wwm.mp3");
			 DBTrack track9 = Trackfactory.createTrack(file9,9);
			 
			 File file10 = new File("WebContent/images/wwm.mp3");
			 DBTrack track10 = Trackfactory.createTrack(file10,10);
			 
	 //categories
			 DBCategory category1 = new DBCategory(1,"Rock");
			 DBCategory category2 = new DBCategory(2,"Pop");
			 DBCategory category3 = new DBCategory(3,"Hip hop");
			 DBCategory category4 = new DBCategory(4,"Rock 'n' Roll");
			 DBCategory category5 = new DBCategory(5,"Rap"); 
			 DBCategory category6 = new DBCategory(6,"Romance");
			 DBCategory category7 = new DBCategory(7,"Opera");
			 DBCategory category8 = new DBCategory(8,"Schlager");
			 DBCategory category9 = new DBCategory(9,"Rap");
			 
	  //Keywords
			 DBKeyword keyword1 = new DBKeyword(1,"Rock");
			 DBKeyword keyword2 = new DBKeyword(2,"pop");
			 DBKeyword keyword3 = new DBKeyword(3,"Hip hop");
			 DBKeyword keyword4 = new DBKeyword(4,"Rap");
			
			 
			
			 
       //Alben 
			 
			 //Album1
			 LinkedList<DBCategory>categories1 = new LinkedList<DBCategory>();
			 categories1.add(category1);
			 categories1.add(category2);
			 
			 LinkedList<DBKeyword>keywords1 = new LinkedList<DBKeyword>();
			 keywords1.add(keyword1);
			 keywords1.add(keyword2);
			 
			 LinkedList<DBTrack>tracks1 = new LinkedList<DBTrack>();
			 tracks1.add(track1);
			 tracks1.add(track2);
			 tracks1.add(track3);
			 tracks1.add(track4);
			 tracks1.add(track5);
			 
			 DBAlbum album1 = new DBAlbum(1,"WebContent/images/cover1.jpg","","",1,25,20,5,"",keywords1,categories1,tracks1);
			 db.store(album1);
			 
			 
			//Album2
			 LinkedList<DBCategory>categories2 = new LinkedList<DBCategory>();
			 categories1.add(category3);
			 categories1.add(category5);
			 
			 LinkedList<DBKeyword>keywords2 = new LinkedList<DBKeyword>();
			 keywords1.add(keyword3);
			 keywords1.add(keyword4);
			 
			 LinkedList<DBTrack>tracks2 = new LinkedList<DBTrack>();
			 tracks1.add(track6);
			 tracks1.add(track7);
			 tracks1.add(track8);
			 tracks1.add(track9);
			 tracks1.add(track10);
			 
			 DBAlbum album2 = new DBAlbum(2,"WebContent/images/cover2.jpg","","",1,30,50,5,"",keywords2,categories2,tracks2);
			 db.store(album2);
			 
			 
		     
		     System.out.println("Daten gespeichert!");
		  }
		  finally {
		     // Schließe die Datenbank
		     db.close();
		  }

	   }

}
