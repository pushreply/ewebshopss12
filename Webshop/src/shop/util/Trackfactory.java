package shop.util;

import java.io.File;
import java.io.IOException;

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
	 * @throws IOException 
	 * @throws NoMP3FrameException 
	 * @throws ID3v2IllegalVersionException 
	 * @throws ID3v2DecompressionException 
	 * @throws ID3v2WrongCRCException 
	 */
	public static DBTrack createTrack(File file) throws IOException, ID3v2WrongCRCException, ID3v2DecompressionException, ID3v2IllegalVersionException, NoMP3FrameException  {
		DBTrack track = new DBTrack();

		// transform mp3 in bytearray and set it in track
			track.setFile(ByteArray.getBytesFromFile(file));


		// convert file to mp3file for vdheide.mp3 tag library
		MP3File mp3File = null;
			mp3File = new MP3File(file.getPath());


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
