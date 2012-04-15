package shop.dto;

import java.util.Arrays;
import java.util.Date;

/**
 * @author roha0001
 *
 */
public class DBTrack {
	private int trackID;
	private byte[] file;
	private String trackTitle;
	private String trackArtist;
	private int trackDate;
	private String trackGenre;
	private int trackNumber;
	private int trackDiskNumber;
	
	public DBTrack() {
		
	}
	
	public DBTrack(int trackID, byte[] file, String trackTitle,
			String trackArtist, int trackDate, String trackGenre,
			int trackNumber, int trackDiskNumber) {
		super();
		this.trackID = trackID;
		this.file = file;
		this.trackTitle = trackTitle;
		this.trackArtist = trackArtist;
		this.trackDate = trackDate;
		this.trackGenre = trackGenre;
		this.trackNumber = trackNumber;
		this.trackDiskNumber = trackDiskNumber;
	}

	public int getTrackID() {
		return trackID;
	}

	public void setTrackID(int trackID) {
		this.trackID = trackID;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getTrackTitle() {
		return trackTitle;
	}

	public void setTrackTitle(String trackTitle) {
		this.trackTitle = trackTitle;
	}

	public String getTrackArtist() {
		return trackArtist;
	}

	public void setTrackArtist(String trackArtist) {
		this.trackArtist = trackArtist;
	}

	public int getTrackDate() {
		return trackDate;
	}

	public void setTrackDate(int trackDate) {
		this.trackDate = trackDate;
	}

	public String getTrackGenre() {
		return trackGenre;
	}

	public void setTrackGenre(String trackGenre) {
		this.trackGenre = trackGenre;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public int getTrackDiskNumber() {
		return trackDiskNumber;
	}

	public void setTrackDiskNumber(int trackDiskNumber) {
		this.trackDiskNumber = trackDiskNumber;
	}

	@Override
	public String toString() {
		return "DBTrack [trackID=" + trackID + ", file="
				+ Arrays.toString(file) + ", trackTitle=" + trackTitle
				+ ", trackArtist=" + trackArtist + ", trackDate=" + trackDate
				+ ", trackGenre=" + trackGenre + ", trackNumber=" + trackNumber
				+ ", trackDiskNumber=" + trackDiskNumber + "]";
	}
	
    
}