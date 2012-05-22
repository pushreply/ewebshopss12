 /**
 * 
 */
package shop.dto;

import java.util.LinkedList;
import java.util.List;
/**
 * @author roha0001,Mukunzi
 * @author sesc0009
 *
 */
public class DBAlbum extends DBUUIDBase{
	private byte[] cover;
	private String albumTitel;
	private String artist;
	private int numberOfDisks;
	private double price;
	private int amount;
	private int numberOfTracks;
	private String label;
	
	List<DBKeyword> keywords = new LinkedList<DBKeyword>();
	List<DBCategory> categories = new LinkedList<DBCategory>();
	List<DBTrack> tracks = new LinkedList<DBTrack>();
	
	//default constructor
	public DBAlbum(){}

	public DBAlbum(byte[] cover, String albumTitel,
			String artist, int numberOfDisks, double price, int amount,
			int numberOfTracks, String label, LinkedList<DBKeyword> keywords,
			LinkedList<DBCategory> categories, LinkedList<DBTrack> tracks) {
		super();
		this.cover = cover;
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

	public byte[] getCover() {
		return cover;
	}

	public void setCover(byte[] coverpath) {
		this.cover = coverpath;
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

	public List<DBKeyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(LinkedList<DBKeyword> keywords) {
		this.keywords = keywords;
	}

	public List<DBCategory> getCategories() {
		return categories;
	}

	public void setCategories(LinkedList<DBCategory> categories) {
		this.categories = categories;
	}

	public List<DBTrack> getTracks() {
		return tracks;
	}

	public void setTracks(DBTrack track) {
		this.tracks.add(track);
	}

	@Override
	public String toString() {
		return "DBAlbum [coverpath=" + cover
				+ ", albumTitel=" + albumTitel + ", artist=" + artist
				+ ", numberOfDisks=" + numberOfDisks + ", price=" + price
				+ ", amount=" + amount + ", numberOfTracks=" + numberOfTracks
				+ ", label=" + label + ", keywords=" + keywords
				+ ", categories=" + categories + ", tracks=" + tracks + "]";
	}

	
}
