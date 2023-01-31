package cs200.team16.chocan;

public class SummaryReport extends Report {
	private ProviderRecord[] providers;
	private int totalFee;
	private int weeklyProviders;
	private int weeklyConsultations;
	
	public SummaryReport() {
      providers = RecordController.getAllProviders();
      weeklyProviders = 0;
      totalFee = 0;
      weeklyConsultations = 0;
      for(int i = 0; i < providers.length; i++) {
    	  ServiceRecord[] services = providers[i].getProviderWeeklyServiceRecords();
    	  if (services.length != 0) {
    		  weeklyProviders++;
    		  totalFee += providers[i].getWeeklyFee();
    		  weeklyConsultations += providers[i].getConsultationCount();
    	  }
      }
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < providers.length; i++) {
	    	 ServiceRecord[] services = providers[i].getProviderWeeklyServiceRecords();
	    	 if (services.length != 0) {
	    		 sb.append("Provider name: " + providers[i].getName() + "\n");
	    		 sb.append("Number of consultations: " + providers[i].getConsultationCount() + "\n");
	    		 sb.append("Weekly fee due: $" + providers[i].getWeeklyFee() + "\n\n");
	    	 }
	     }
		
		sb.append("Total providers: " + weeklyProviders + "\n");
		sb.append("Total number of consultations: " + weeklyConsultations + "\n");
		sb.append("Overall fee total: $" + totalFee + "\n");
		
		return sb.toString();
	}
}
