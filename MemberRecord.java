package cs200.team16.chocan;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stores information for a ChocAn member.
 *
 * @author Kaitlyn NeSmith
 */
public class MemberRecord extends PersonRecord {

  /**
   * A string representing this MemberRecord's status.
   * Can be either "active" or "suspended".
   */
  private String status = "active";
  
  /**
   * Constructs an active, populated MemberRecord.
   */
  public MemberRecord(String name, int number, Address address) {
    super(name, number, address);
  }
  
  /**
   * Gets the current status of this MemberRecord.
   * Can be either "active" or "suspended".
   *
   * @return A string representing the status of this MemberRecord.
   */
  public String getStatus() {
    return status;
  }
  
  /**
   * Sets the current status of this MemberRecord.
   * Status must be either "active" or "suspended".
   *
   * @param status The new status to be assigned to this MemberRecord.
   */
  public void setStatus(String status) {
    this.status = status;
  }
  
  /**
   * Gets an array of ServiceRecords whose time filed was in the past week by this member.
   *
   * @return An array of the past week's ServiceRecords filled by this member.
   */
  public ServiceRecord[] getMemberWeeklyServiceRecords() {
      ArrayList<ServiceRecord> temp = new ArrayList<ServiceRecord>();
      ServiceRecord[] allServiceRecordsInPastWeek = RecordController.getWeeklyServiceRecords();
      for (int i = 0; i < allServiceRecordsInPastWeek.length; i++) {
  	  if (allServiceRecordsInPastWeek[i].getMember().accountNumber == this.accountNumber) {
  		  temp.add(allServiceRecordsInPastWeek[i]);
  	  }
      }
  		
      ServiceRecord[] tempArr = new ServiceRecord[temp.size()];
      temp.toArray(tempArr);
      return tempArr;
    }
}
