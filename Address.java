package cs200.team16.chocan;

/**
 * class to store address.

 * @author michael reis
 */

public class Address {
  private String streetAddress;
  private String city;
  private String state;
  private String zipCode;
  /**
  * constructor for the class.

  * @param street is the street address of the address
  * @param city is the city of the address
  * @param state is the state of the address
  * @param zip is the zip code of the address
  */
  public Address(String street, String city, String state, String zip) {
    this.streetAddress = street;
    this.city = city;
    this.state = state;
    this.zipCode = zip;
  }
  /**
  * gets the street address of the address.

  * @return the street address of the address
  */
  
  public String getStreetAddress() {
    return streetAddress;
  }
  /**
  * sets the street address of the address.

  * @param street is the street address of the address
  */
  public void setStreetAddress(String street) {
    streetAddress = street;
  }
  /**
   * gets the city of the address.

   * @return the city of the address
   */
  public String getCity() {
    return city;
  }
  /**
   * sets the city of the address.

   * @param city is the city of the address
   */
  public void setCity(String city) {
    this.city = city;
  }
  /**
   * gets the state of the address.

   * @return the state of the address
   */
  public String getState() {
    return state;
  }
  /**
   * sets the state of the address.

   * @param state is the state of the address
   */
  public void setState(String state) {
    this.state = state;
  }
  /**
   * gets the zip code of the address.

   * @return the zip code of the address
   */
  public String getZipCode() {
    return zipCode;
  }
  /**
   * sets the zip code of the address.

   * @param zip is the zip code of the address
   */
  public void setZipCode(String zip) {
    zipCode = zip;
  }

  public String toString(){
	String a = streetAddress + "\n";
	a += city;
	a += ", ";
	a += state;
	a += ", ";
	a += zipCode;
	return a;
  }
	
}
