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

	/**
	 * Creates a singe DBTrack based on an mp3
	 * 
	 * @param file
	 *            the mp3 you want to create the {@link #DBTrack} for.
	 * @return the DBTrack file with fulfilled attributes from the ID3Tag of the
	 *         given file
	 */
	public static DBTrack createTrack(File file) {
		DBTrack track = new DBTrack();

		// transform mp3 in bytearray and set it in track
		try {
			track.setFile(ByteArray.getBytesFromFile(file));
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

		// read mp3 tags and set corresponding attributes in the track
		try {

			// ARTIST
			String trackArtistString = mp3File.getArtist().getTextContent();
			if (trackArtistString == null || trackArtistString.isEmpty()) {
				track.setTrackArtist("unknownArtist");
			} else {
				track.setTrackArtist(trackArtistString);
			}

			// DISKNUMBER
			String trackDiskNumberString = mp3File.getPartOfSet()
					.getTextContent();
			if (trackDiskNumberString == null || trackDiskNumberString.isEmpty()) {
				track.setTrackDiskNumber(1);
			} else {
				track.setTrackDiskNumber(new Integer(trackDiskNumberString
						.split("/")[0]).intValue());
				// .getTrack().getTextContent() gibt Track in der Art '5/14'
				// aus!...daher split
			}

			// GENRE
			String trackGenreString = mp3File.getGenre().getTextContent();
			if (trackGenreString == null || trackGenreString.isEmpty()) {
				track.setTrackGenre("unknownGenre");
			} else {
				track.setTrackGenre(trackGenreString);
			}

			// TRACKNUMBER
			String trackNumberString = mp3File.getTrack().getTextContent();
			if (trackNumberString == null || trackNumberString.isEmpty()) {
				track.setTrackNumber(0);
			} else {
				track.setTrackNumber(new Integer(
						trackNumberString.split("/")[0]).intValue());
			}

			// TITLE
			String trackTitleString = mp3File.getTitle().getTextContent();
			if (trackTitleString == null || trackTitleString.isEmpty()) {
				track.setTrackTitle("unknownTitle");
			} else {
				track.setTrackTitle(trackTitleString);
			}

			// DATE
			String trackDateString = mp3File.getYear().getTextContent();
			if (trackDateString == null || trackDateString.isEmpty()) {
				track.setTrackDate(0);
			} else {
				track.setTrackDate(new Integer(trackDateString));
			}
		} catch (FrameDamagedException e) {
			e.printStackTrace();
		}

		return track;
	}
}
