package cs200.team16.chocan;

import java.util.ArrayList;
/**
 * abstract class for record classes to inherit.

 * @author Michael Reis
 */
public abstract class PersonRecord {
  protected String name;
  protected int accountNumber;
  protected Address address;
  protected ArrayList<ServiceRecord> services;

  /**
   * constructor for the class.

   * @param name is the person's name
   * @param number is the person's account number
   * @param address is the person's address
   */
  public PersonRecord(String name, int number, Address address) {
    this.name = name;
    this.accountNumber = number;
    this.address = address;
    services = new ArrayList<ServiceRecord>();
  }
  /**
   * gets person's name.

   * @return the name of the person
   */
  public String getName() {
    return name;
  }
  /**
   * sets person's name.

   * @param name is the person's name
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * gets the person's account number.

   * @return the account number of the person
   */
  public int getAccountNumber() {
    return accountNumber;
  }
  /**
   * sets person's account number.

   * @param number is the person's account number
   */
  public void setAccountNumber(int number) {
    accountNumber = number;
  }
  /**
   * gets the person's address.

   * @return the address of the person
   */
  public Address getAddress() {
    return address;
  }
  /**
   * sets the person's address.

   * @param address is the person's address
   */
  public void setAddress(Address address) {
    this.address = address;
  }
  /**
   * gets person's service records.

   * @return temp is an array of person's service records
   */
  public ServiceRecord[] getServiceRecords() {
    ServiceRecord[] temp = new ServiceRecord[services.size()];
    services.toArray(temp);
    return temp;
  }
  /**
   * adds service to person's service record.

   * @param record is the service recorded to add
   */
  public void addServiceRecord(ServiceRecord record) {
    services.add(record);
  }
  /**
   * removes service from person's service record.

   * @param record is the service record to remove
   */
  public void removeServiceRecord(ServiceRecord record) {
    services.remove(record);
  }
     
    
}
