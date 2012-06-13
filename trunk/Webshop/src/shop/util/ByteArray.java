package shop.util;


/**
 * @author sesc0009
 * Eine Datei in Byte-Array umwandeln
 * 
 * Ein Byte-Array ins eine Datei umwandeln
 *
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArray {
	

	// Returns the contents of the file in a byte array.
		public static byte[] getBytesFromFile(File file) throws IOException {
			InputStream is = new FileInputStream(file);

			// Get the size of the file
			long length = file.length();

			// You cannot create an array using a long type.
			// It needs to be an int type.
			// Before converting to an int type, check
			// to ensure that file is not larger than Integer.MAX_VALUE.
			if (length > Integer.MAX_VALUE) {
				// File is too large
			}

			// Create the byte array to hold the data
			byte[] bytes = new byte[(int) length];

			// Read in the bytes
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}

			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file "
						+ file.getName());
			}

			// Close the input stream and return bytes
			is.close();
			return bytes;
		}
	
	
	static public byte[] inputStreamToByteArray(InputStream inStream) throws IOException { 
	    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	    byte[] buffer = new byte[8192]; 
	    int bytesRead; 
	    while ((bytesRead = inStream.read(buffer)) > 0) { 
	        baos.write(buffer, 0, bytesRead); 
	    } 
	    return baos.toByteArray(); 
	} 

	/*
	 * ByteArray.byteArrayToFile(bytearray, ausDateiname);
	 */
	
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
