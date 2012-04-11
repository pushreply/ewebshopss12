package shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import shop.dto.DBTrack;
import de.vdheide.mp3.FrameDamagedException;
import de.vdheide.mp3.ID3v2DecompressionException;
import de.vdheide.mp3.ID3v2IllegalVersionException;
import de.vdheide.mp3.ID3v2WrongCRCException;
import de.vdheide.mp3.MP3File;
import de.vdheide.mp3.NoMP3FrameException;

/**
 * Create
 * 
 * @author Andreas
 * 
 */
public class Trackfactory {

	// Returns the contents of the file in a byte array.
	private static byte[] getBytesFromFile(File file) throws IOException {
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

	/**
	 * Creates a singe DBTrack based on an mp3
	 * 
	 * @param file
	 *            the mp3 you want to create the {@link #DBTrack} for.
	 * @return the DBTrack file with fulfilled attributes from the ID3Tag of the
	 *         given file
	 */
	public static DBTrack createTrack(File file, int id) {
		DBTrack track = new DBTrack();

		// transform mp3 in bytearray and set it in track
		try {
			track.setFile(getBytesFromFile(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// convert file to mp3file for vdheide.mp3 tag library
		MP3File mp3File = null;
		try {
			mp3File = new MP3File(file.getPath());
		} catch (ID3v2WrongCRCException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ID3v2DecompressionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ID3v2IllegalVersionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoMP3FrameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//read mp3 tags and set corresponding attributes in the track
		try {
			track.setTrackArtist(mp3File.getArtist().getTextContent());
			track.setTrackDiskNumber(new Integer(mp3File.getPartOfSet()
					.getTextContent().split("/")[0]).intValue()); // .getTrack().getTextContent() gibt Track in der Art '5/14' aus!...daher split
			track.setTrackGenre(mp3File.getGenre().getTextContent());
			track.setTrackNumber(new Integer(mp3File.getTrack()
					.getTextContent().split("/")[0]).intValue());
			track.setTrackTitle(mp3File.getTitle().getTextContent());
			track.setTrackDate(new Integer(mp3File.getYear().getTextContent()));
		} catch (FrameDamagedException e) {
			e.printStackTrace();
		}
		
		track.setTrackID(id);

		return track;
	}
}
