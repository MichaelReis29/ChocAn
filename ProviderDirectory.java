package cs200.team16.chocan;


public class ProviderDirectory {

	private static ProviderDirectoryEntry[] entries = 
	{
		new ProviderDirectoryEntry("Dietitian", "4001", 29),
		new ProviderDirectoryEntry("Aerobic Exercise Session", "5001", 12),
		new ProviderDirectoryEntry("Therapy", "4002", 49)
	};
	
	public static ProviderDirectoryEntry searchByCode(String serviceCode) {
		// if the for loop doesn't find a matching entry, null is returned by default
		ProviderDirectoryEntry returnCode = null;
		for(int i = 0; i < entries.length; i++) {
			if(entries[i].getServiceCode().equals(serviceCode)) {
				returnCode = entries[i];
			}
		}
		return returnCode;
	}
	public static String compileProviderDirectory() {
		String concat = "";
		for(int i = 0; i < entries.length; i++) {
			concat += entries[i].getServiceName() + '\n' + entries[i].getServiceCode() + '\n' + entries[i].getServiceFee() + '\n';
		}
		return concat;
	}

}

