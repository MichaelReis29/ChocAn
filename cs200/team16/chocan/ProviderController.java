package cs200.team16.chocan;


public class ProviderController
{
	ProviderDirectory directory;
	ProviderController(ProviderDirectory directory){
		this.directory.equals(directory);
	}
	public static String requestProviderDirectory() {
		String directoryStr = ProviderDirectory.compileProviderDirectory();
		return directoryStr;
	}
	public static ProviderDirectoryEntry getProviderDirectoryEntry(String serviceCode) {
		return ProviderDirectory.searchByCode(serviceCode);
	}
	
}
