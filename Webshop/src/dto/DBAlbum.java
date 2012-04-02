/**
 * 
 */
package dto;
import com.db4o.*;

/**
 * @author roha0001
 *
 */
public class DBAlbum {
	private String coverpath;
	private String albumTitel;
	private String artist;
	private int numberOfDisks;
	private double price;
	private int amount;
	private int numberOfTracks;
	private String label;
	
	public DBAlbum(String coverpath, String albumTitel, String artist,
			int numberOfDisks, double price, int amount, int numberOfTracks,
			String label) {
		super();
		this.coverpath = coverpath;
		this.albumTitel = albumTitel;
		this.artist = artist;
		this.numberOfDisks = numberOfDisks;
		this.price = price;
		this.amount = amount;
		this.numberOfTracks = numberOfTracks;
		this.label = label;
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

	@Override
	public String toString() {
		return "DBAlbum [coverpath=" + coverpath + ", albumTitel=" + albumTitel
				+ ", artist=" + artist + ", numberOfDisks=" + numberOfDisks
				+ ", price=" + price + ", amount=" + amount
				+ ", numberOfTracks=" + numberOfTracks + ", label=" + label
				+ "]";
	}
	
	
}
