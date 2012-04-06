 /**
 * 
 */
package shop.dto;

import java.util.LinkedList;

/**
 * @author roha0001
 *
 */
public class DBAlbum {
	private int albID;
	private String coverpath;
	private String albumTitel;
	private String artist;
	private int numberOfDisks;
	private double price;
	private int amount;
	private int numberOfTracks;
	private String label;
	
	LinkedList<DBKeyword> keywords = new LinkedList<DBKeyword>();
	LinkedList<DBCategory> categories = new LinkedList<DBCategory>();
	LinkedList<DBTrack> tracks = new LinkedList<DBTrack>();
	
	//default constructor
	public DBAlbum(){}

	public DBAlbum(int albID, String coverpath, String albumTitel,
			String artist, int numberOfDisks, double price, int amount,
			int numberOfTracks, String label, LinkedList<DBKeyword> keywords,
			LinkedList<DBCategory> categories, LinkedList<DBTrack> tracks) {
		super();
		this.albID = albID;
		this.coverpath = coverpath;
		this.albumTitel = albumTitel;
		this.artist = artist;
		this.numberOfDisks = numberOfDisks;
		this.price = price;
		this.amount = amount;
		this.numberOfTracks = numberOfTracks;
		this.label = label;
		this.keywords = keywords;
		this.categories = categories;
		this.tracks = tracks;
	}

	public int getAlbID() {
		return albID;
	}

	public void setAlbID(int albID) {
		this.albID = albID;
	}

	public String getCoverpath() {
		return coverpath;
	}

	public void setCoverpath(String coverpath) {
		this.coverpath = coverpath;
	}

	public String getAlbumTitel() {
		return albumTitel;
	}

	public void setAlbumTitel(String albumTitel) {
		this.albumTitel = albumTitel;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getNumberOfDisks() {
		return numberOfDisks;
	}

	public void setNumberOfDisks(int numberOfDisks) {
		this.numberOfDisks = numberOfDisks;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public LinkedList<DBKeyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(LinkedList<DBKeyword> keywords) {
		this.keywords = keywords;
	}

	public LinkedList<DBCategory> getCategories() {
		return categories;
	}

	public void setCategories(LinkedList<DBCategory> categories) {
		this.categories = categories;
	}

	public LinkedList<DBTrack> getTracks() {
		return tracks;
	}

	public void setTracks(LinkedList<DBTrack> tracks) {
		this.tracks = tracks;
	}

	@Override
	public String toString() {
		return "DBAlbum [albID=" + albID + ", coverpath=" + coverpath
				+ ", albumTitel=" + albumTitel + ", artist=" + artist
				+ ", numberOfDisks=" + numberOfDisks + ", price=" + price
				+ ", amount=" + amount + ", numberOfTracks=" + numberOfTracks
				+ ", label=" + label + ", keywords=" + keywords
				+ ", categories=" + categories + ", tracks=" + tracks + "]";
	}

	
}
