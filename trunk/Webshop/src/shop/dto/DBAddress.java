/**
 * 
 */
package shop.dto;

/**
 * @author roha0001
 *
 */
public class DBAddress {
	private int id_dbadress;
	private String street;
	private String country; 
	private String firstName;
	private String lastName;
	private String gender;
	
	public DBAddress(String street, String country, String firstName,
			String lastName, String gender) {
		super();
		this.street = street;
		this.country = country;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getId_dbadress() {
		return id_dbadress;
	}

	public void setId_dbadress(int id_dbadress) {
		this.id_dbadress = id_dbadress;
	}

	@Override
	public String toString() {
		return "DBAddress [street=" + street + ", country=" + country
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + "]";
	}

	

}
