package shop.util;

import java.io.File;

/**
 * wird benötigt beim Löschen der MP3-Dateien und Albumcover, nachden sie in der
   db gespeichert wurden.
 * @author mukunzi
 *
 */
public class DeleteFile {
	 
	public void deleteFile(String file)
	{
		 try {
			File datei = new File("WebContent/images/"+file);
			    if (datei.exists()) {
			      datei.delete();
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
