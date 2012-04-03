package shop.util;


/**
 * @author sesc0009
 * Eine Datei in Byte-Array umwandeln
 * 
 * Ein Byte-Array ins eine Datei umwandeln
 *
 */
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArray {
	
/*	
 * eine Datei in Byte-Array umwandlen
 * try {
        FileInputStream file = new FileInputStream("C:/Users/HP-Master/Desktop/FH/"+sDateiname);
        bytesarray = ByteArray.inputStreamToByteArray(file);
    } catch (FileNotFoundException e) { 
        e.printStackTrace(); 
    }
 */ 
	static public byte[] inputStreamToByteArray(InputStream inStream) throws IOException { 
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    byte[] buffer = new byte[8192]; 
	    int bytesRead; 
	    while ((bytesRead = inStream.read(buffer)) > 0) { 
	        baos.write(buffer, 0, bytesRead); 
	    } 
	    return baos.toByteArray(); 
	} 

	
	
	static public void byteArrayToFile(byte[] byteArray, String outFilePath) throws IOException
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(outFilePath);
			fos.write(byteArray);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} 

}
