package cs200.team16.chocan;

public class ProviderDirectoryEntry {
	private String serviceName;
	private String serviceCode;
	private int serviceFee;
	ProviderDirectoryEntry(String serviceName, String serviceCode, int serviceFee){
		this.serviceName = serviceName;
		this.serviceCode = serviceCode;
		this.serviceFee = serviceFee;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public int getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(int serviceFee) {
		this.serviceFee = serviceFee;
	}
}
