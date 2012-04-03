package shop.dto;

import java.util.Date;

/**
 * @author roha0001
 *
 */
public class DBTrack {
	private int id_dbtrack;
	private byte[] file;
	private String trackTitle;
	private String trackArtist;
	private Date trackDate;
	private String trackGenre;
	private int trackNumber;
	private int trackDiskNumber;
	
	public DBTrack() {
		
	}
	
	public DBTrack(int tracknr, byte[] file, String trackTitle, String trackArtist,
			Date trackDate, String trackGenre, int trackNumber,
			int trackDiskNumber) {
		super();
		this.id_dbtrack = tracknr;
		this.file = file;
		this.trackTitle = trackTitle;
		this.trackArtist = trackArtist;
		this.trackDate = trackDate;
		this.trackGenre = trackGenre;
		this.trackNumber = trackNumber;
		this.trackDiskNumber = trackDiskNumber;
	}
	
	public int getTracknr() {
		return id_dbtrack;
	}
	
	public void setTracknr(int tracknr) {
		this.id_dbtrack = tracknr;
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
	public Date getTrackDate() {
		return trackDate;
	}
	public void setTrackDate(Date trackDate) {
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

	
}
