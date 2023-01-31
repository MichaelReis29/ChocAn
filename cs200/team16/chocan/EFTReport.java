package cs200.team16.chocan;

public class EFTReport extends Report {
  private String name;
  private int accountNumber;
  private int feeDue;

  public EFTReport(ProviderRecord record) {
    name = record.getName();
    accountNumber = record.getAccountNumber();
    feeDue = record.getWeeklyFee();
  }
  
  // Compatibility with name output in ReportController
  public String getProviderName() {
	  return name;
  }
	
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("Provider name: " + name + "\n");
    sb.append("Provider number: " + accountNumber + "\n");
    sb.append("Amount to transfer: $" + feeDue + "\n");
    
    return sb.toString();
  }
}