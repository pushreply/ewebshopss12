/**
 * 
 */
package shop.dto;

/**
 * @author roha0001,Mukunzi
 *
 */
public class DBAddress extends DBUUIDBase{
	private String street;
	private String country; 
	private String firstName;
	private String lastName;
	private String gender;
	private String art; //delivery or billing
	
	
	//Default Constructor
	public DBAddress(){
		
	}
	
	public DBAddress(String street, String country,
			String firstName, String lastName, String gender, String art) {
		super();
		this.street = street;
		this.country = country;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.art = art;
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

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	@Override
	public String toString() {
		return "DBAddress [street=" + street
				+ ", country=" + country + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", art="
				+ art + "]";
	}

	

}
