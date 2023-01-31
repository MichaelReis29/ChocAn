package cs200.team16.chocan;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stores & modifies all records within the ChocAn system.
 *
 * @author Kaitlyn NeSmith
 */
public class RecordController {

  private static ArrayList<MemberRecord> memberRecords = new ArrayList<MemberRecord>();
  private static ArrayList<ProviderRecord> providerRecords = new ArrayList<ProviderRecord>();
  private static ArrayList<OperatorRecord> operatorRecords = new ArrayList<OperatorRecord>();
  private static ArrayList<ManagerRecord> managerRecords = new ArrayList<ManagerRecord>();
  private static ArrayList<ServiceRecord> serviceRecords = new ArrayList<ServiceRecord>();
  
  /**
   * Adds a new ProviderRecord to this RecordController.
   *
   * @param provider The new ProviderRecord to be added.
   */
  public static void addProvider(ProviderRecord provider) {
    providerRecords.add(provider);
  }

  /**
   * Adds a new OperatorRecord to this RecordController.
   *
   * @param operator The new OperatorRecord to be added.
   */
  public static void addOperator(OperatorRecord operator)
  {
    operatorRecords.add(operator);
  }
  
  /**
   * Attempts to delete a ProviderRecord from this RecordController.
   *
   * @param number The account number of the ProviderRecord to delete.
   */
  public static void deleteProvider(int number) {
    for (int i = 0; i < providerRecords.size(); i++) {
      if (providerRecords.get(i).getAccountNumber() == number) {
        providerRecords.remove(i);
        return;
      }
    }
  }
  
  /**
   * Attempts to retrieve a ProviderRecord from this RecordController.
   *
   * @param number The account number of the ProviderRecord to retrieve.
   * @return The ProviderRecord with a matching account number; if not ProviderRecord with a matching account number is found, returns null.
   */
  public static ProviderRecord getProvider(int number) {
    for (int i = 0; i < providerRecords.size(); i++) {
      if (providerRecords.get(i).getAccountNumber() == number) {
        return providerRecords.get(i);
      }
    }
    return null;
  }
  
  /**
   * Returns all ProviderRecords present in this RecordController.
   *
   * @return An array of all present ProviderRecords.
   */
  public static ProviderRecord[] getAllProviders() {
    ProviderRecord[] tempArr = new ProviderRecord[providerRecords.size()];
    providerRecords.toArray(tempArr);
    return tempArr;
  }

  /**
   * Adds a new MemberRecord to this RecordController.
   *
   * @param provider The new MemberRecord to be added.
   */
  public static void addMember(MemberRecord member) {
    memberRecords.add(member);
  }
  
  /**
   * Attempts to delete a MemberRecord from this RecordController.
   *
   * @param number The account number of the MemberRecord to delete.
   */
  public static void deleteMember(int number) {
    for (int i = 0; i < memberRecords.size(); i++) {
      if (memberRecords.get(i).getAccountNumber() == number) {
        memberRecords.remove(i);
        return;
      }
    }
  }
  
  /**
   * Attempts to retrieve a MemberRecord from this RecordController.
   *
   * @param number The account number of the MemberRecord to retrieve.
   * @return The MemberRecord with a matching account number; if not MemberRecord with a matching account number is found, returns null.
   */
  public static MemberRecord getMember(int number) {
    for (int i = 0; i < memberRecords.size(); i++) {
      if (memberRecords.get(i).getAccountNumber() == number) {
        return memberRecords.get(i);
      }
    }
    return null;
  }
  
  /**
   * Returns all MemberRecords present in this RecordController.
   *
   * @return An array of all present MemberRecords.
   */
  public static MemberRecord[] getAllMembers() {
    MemberRecord[] tempArr = new MemberRecord[memberRecords.size()];
    memberRecords.toArray(tempArr);
    return tempArr;
  }

  /**
   * Adds a new ManagerRecord to this RecordController.
   *
   * @param manager The new ManagerRecord to be added.
   */
  public static void addManager(ManagerRecord manager) {
    managerRecords.add(manager);
  }
  
  /**
   * Gets a ManagerRecord from this RecordController via an account number.
   *
   * @param pin The ManagerRecord's account number.
   * @return The ManagerRecord found with a matching account number; if no ManagerRecord is found with a matching account number, returns null.
   */
  public static ManagerRecord getManager(int number) {
    for (int i = 0; i < managerRecords.size(); i++) {
      if (managerRecords.get(i).getAccountNumber() == number) {
	    return managerRecords.get(i);
      }
    }
    
    return null;
  }
  
  /**
   * Gets an OperatorRecord from this RecordController via an account number.
   *
   * @param pin The OperatorRecord's account number.
   * @return The OperatorRecord found with a matching account number; if no OperatorRecord is found with a matching account number, returns null.
   */
  public static OperatorRecord getOperator(int number) {
    for (int i = 0; i < operatorRecords.size(); i++) {
      if (operatorRecords.get(i).getAccountNumber() == number) {
        return operatorRecords.get(i);
      }
    }
    
    return null;
  }

  /**
   * Adds a new ServiceRecord to this RecordController.
   *
   * @param provider The new ServiceRecord to be added.
   */
  public static void addServiceRecord(ServiceRecord service)
  {
    serviceRecords.add(service);
  }
  
  /**
   * Gets an array of ServiceRecords whose time filed was in the past week.
   *
   * @return An array of the past week's ServiceRecords.
   */
  public static ServiceRecord[] getWeeklyServiceRecords() {
    ArrayList<ServiceRecord> temp = new ArrayList<ServiceRecord>();
    // This will allow us to compare which ServiceRecords have occurred within this week.
    LocalDateTime weekStart = LocalDateTime.now().minusDays(7);
	for (int i = 0; i < serviceRecords.size(); i++)
    {
      if (serviceRecords.get(i).getTimeFiled().isAfter(weekStart)) {
        temp.add(serviceRecords.get(i));
      }
    }
	
	ServiceRecord[] tempArr = new ServiceRecord[temp.size()];
	temp.toArray(tempArr);
    return tempArr;
  }
  
  /**
   * Retrieve a ServiceRecord with the specified ID
   * 
   * @param id The ID of the ServiceRecord to be retrieved
   * @return The retrieved ServiceRecord
   */
  public static ServiceRecord getServiceRecordByID(String id) {
	  ServiceRecord result = null;
	  for(int i = 0; i < serviceRecords.size(); i++) {
		  if (serviceRecords.get(i).getID() == id) {
			  result = serviceRecords.get(i);
			  break;
		  }
	  }
	  return result;
  }
  
  /**
   * Delete ServiceRecord with the specified ID
   * 
   * @param id The ID of the ServiceRecord to be deleted
   */
  public static void deleteServiceRecord(String id) {
	  for(int i = 0; i < serviceRecords.size(); i++) {
		  if (serviceRecords.get(i).getID() == id) {
			  serviceRecords.remove(i);
			  break;
		  }
	  }
  }
  
  
}
