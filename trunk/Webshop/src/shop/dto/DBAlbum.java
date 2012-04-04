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
	private int id_dbalbum;
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

	public int getId_dbalbum() {
		return id_dbalbum;
	}

	public void setId_dbalbum(int id_dbalbum) {
		this.id_dbalbum = id_dbalbum;
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
		return "DBAlbum [id_dbalbum=" + id_dbalbum + ", coverpath=" + coverpath
				+ ", albumTitel=" + albumTitel + ", artist=" + artist
				+ ", numberOfDisks=" + numberOfDisks + ", price=" + price
				+ ", amount=" + amount + ", numberOfTracks=" + numberOfTracks
				+ ", label=" + label + ", keywords=" + keywords
				+ ", categories=" + categories + ", tracks=" + tracks + "]";
	}
	
	
	
	
}
