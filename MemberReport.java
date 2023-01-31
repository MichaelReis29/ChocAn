package cs200.team16.chocan;

public class MemberReport extends Report {
  private String name;
  private int accountNumber;
  private Address address;
  private ServiceRecord[] services;
  
  public MemberReport(MemberRecord record){
    name = record.getName();
    accountNumber = record.getAccountNumber();
    address = record.getAddress();
    services = record.getMemberWeeklyServiceRecords();
  }
  
  // Added for compatibility with file output style in ReportController
  public String getMemberName() {
	  return name;
  }
  
  @Override
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append("Member name: " + name + "\n");
	  sb.append("Member number: " + accountNumber + "\n");
	  sb.append("Member street address: " + address.getStreetAddress() + "\n");
	  sb.append("Member city: " + address.getCity() + "\n");
	  sb.append("Member state: " + address.getState() + "\n");
	  sb.append("Member ZIP code: " + address.getZipCode() + "\n");
	  
	  sb.append("Services utilized:\n");
	  for(int i = 0; i < services.length; i++) {
		  sb.append("\tDate of service: " + services[i].getTimeProvided() + "\n");
		  sb.append("\tProvider name: " + services[i].getProvider().getName() + "\n");
		  sb.append("\tService name: " + services[i].getServiceProvided().getServiceName() + "\n\n");
	  }
	  
	  return sb.toString();
  }
}
