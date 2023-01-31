package cs200.team16.chocan;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a provider's record.
 *
 * @author Faruq Yusuff
 */
public class ProviderRecord extends PersonRecord {
    public ProviderRecord(String name, int number, Address address, int pin) {
        super(name, number, address);
        this.pin = pin;
    }

   
    private int pin;


    /**
     * Verify provider's pin.
     *
     * @return provider's pin
     */
    public Boolean isPin(int pin) {
        return this.pin == pin;
    }

    /**
     * Change the provider's pin.
     * 
     * @return void
     */
    public void setPin(int newPin){
        this.pin = newPin;
    }
    
    /**
     * Gets the current weekly fee due to this provider.
     *
     * @return Weekly fee due
     */
    public int getWeeklyFee() {
    	ServiceRecord[] providerServiceRecordsInPastWeek = getProviderWeeklyServiceRecords();
    	int weeklyFee = 0;
    	for(int i = 0; i < providerServiceRecordsInPastWeek.length; i++) {
    		weeklyFee += providerServiceRecordsInPastWeek[i].getServiceProvided().getServiceFee();
    	}
    	return weeklyFee;
    }
    
    
    /**
     * Gets the consultation count of this provider.
     *
     * @return consultation count
     */
    public int getConsultationCount() {
    	return getProviderWeeklyServiceRecords().length;
    }
    
    /**
     * Gets an array of ServiceRecords whose time filed was in the past week by this provider.
     *
     * @return An array of the past week's ServiceRecords filled by this provider.
     */
    public ServiceRecord[] getProviderWeeklyServiceRecords() {
        ArrayList<ServiceRecord> temp = new ArrayList<ServiceRecord>();
        ServiceRecord[] allServiceRecordsInPastWeek = RecordController.getWeeklyServiceRecords();
        for (int i = 0; i < allServiceRecordsInPastWeek.length; i++) {
    	  if (allServiceRecordsInPastWeek[i].getProvider().accountNumber == this.accountNumber) {
    		  temp.add(allServiceRecordsInPastWeek[i]);
    	  }
        }
    		
        ServiceRecord[] tempArr = new ServiceRecord[temp.size()];
        temp.toArray(tempArr);
        return tempArr;
      }
}
