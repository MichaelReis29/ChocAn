package cs200.team16.chocan;

public class ProviderReport extends Report {
  private String providerName;
  private int providerNumber;
  private Address address;
  private ServiceRecord[] services;
  private int consultCount;
  private int totalFee;
  
  public ProviderReport(ProviderRecord record) {
	  providerName = record.getName();
	  providerNumber = record.getAccountNumber();
	  address = record.getAddress();
	  services = record.getProviderWeeklyServiceRecords();
	  consultCount = record.getConsultationCount();
	  totalFee = record.getWeeklyFee();
  }
  
  // Add for ReportController file name compatibility
  public String getProviderName() {
	  return providerName;
  }
  
  @Override
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  
	  sb.append("Provider name: " + providerName + "\n");
	  sb.append("Provider number: " + providerNumber + "\n");
	  sb.append("Provider street address: " + address.getStreetAddress() + "\n");
	  sb.append("Provider city: " + address.getCity() + "\n");
	  sb.append("Provider state: " + address.getState() + "\n");
	  sb.append("Provider ZIP code: " + address.getZipCode() + "\n");
	  
	  sb.append("Services provided:\n");
	  for(int i = 0; i < services.length; i++) {
		  sb.append("\tDate of service: " + services[i].getTimeProvided() + "\n");
		  sb.append("\tDate and time data were received by the computer: " + services[i].getTimeFiled() + "\n");
		  sb.append("\tMember name: " + services[i].getMember().getName() + "\n");
		  sb.append("\tMember number: " + services[i].getMember().getAccountNumber() + "\n");
		  sb.append("\tService code: " + services[i].getServiceProvided().getServiceCode() + "\n");
		  sb.append("\tFee to be paid: $" + services[i].getServiceProvided().getServiceFee() + "\n\n");
	  }

	  sb.append("Total number of consultations: " + consultCount + "\n");
	  sb.append("Total fee for week: $" + totalFee + "\n");
	  
	  return sb.toString();
  }
}
