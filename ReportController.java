package cs200.team16.chocan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Handles generating and writing reports to the disk.
 *
 * @author Sawyer Griffy
 *
 */
public class ReportController
{

	/**
	 * Generates and writes a member report to the disk.
	 *
	 * @param userNumber The account number of the member to generate a report for.
	 */
    public static void generateMemberReport(int userNumber) {
    	MemberRecord out = RecordController.getMember(userNumber);
    	MemberReport report = new MemberReport(out);
    	String output = report.toString();
    	
    	writeReport("./reports/member/", report.getMemberName() + "_" + getCurrentDate(), output);
    }

    /**
     * Generates and writes a provider report to the disk.
     *
     * @param userNumber The account number of the provider to generate a report for.
     */
    public static void generateProviderReport(int userNumber) {
  	    ProviderRecord out = RecordController.getProvider(userNumber);
    	ProviderReport report = new ProviderReport(out);
    	String output = report.toString();
    	
    	writeReport("./reports/provider/", report.getProviderName() + "_" + getCurrentDate(), output);
    }

    /**
     * Generates and writes a summary report to the disk.
     */
    public static void generateSummaryReport() {
    	SummaryReport report = new SummaryReport();
    	String output = report.toString();
    	writeReport("./reports/summary/", "Summary_" + getCurrentDate(), output);
    }

    /**
     * Generates and writes an EFT report to the disk.
     *
     * @param userNumber The account number of the provider to generate a report for.
     */
    public static void generateEFT(int userNumber) {
    	ProviderRecord out = RecordController.getProvider(userNumber);
    	EFTReport report = new EFTReport(out);
    	String output = report.toString();
    	writeReport("./reports/eft/", report.getProviderName() + "_" + getCurrentDate(), output);
    }
    
    /**
     * Runs the ChocAn weekly main accounting procedure.
     */
    public static void runMainAccountingProcedure()
    {
    	// First, weekly provider records & EFTs
    	ProviderRecord[] providers = RecordController.getAllProviders();
    	for (int i = 0; i < providers.length; i++)
    	{
    		if (providers[i].getConsultationCount() != 0)
    		{
    			generateProviderReport(providers[i].getAccountNumber());
    			generateEFT(providers[i].getAccountNumber());
    		}
    	}
    	
    	// Then, generate weekly member reports
    	MemberRecord[] members = RecordController.getAllMembers();
    	for (int i = 0; i < members.length; i++)
    	{
    		if (members[i].getMemberWeeklyServiceRecords().length != 0)
    			generateMemberReport(members[i].getAccountNumber());
    	}
    	
    	// Finally, summary
    	generateSummaryReport();
    }
    
    /**
     * Internal function for writing reports to disk.
     *
     * @param outDir The output directory in which the report is to be written.
     * @param fileName The name of the report to write.
     * @param report The report itself.
     */
    private static void writeReport(String outDir, String fileName, String report) {
		try {
			Files.createDirectories(Paths.get(outDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Path fileOutput = Paths.get(outDir + fileName.replaceAll(" ", "") + ".txt");
    	try {
			Files.write(fileOutput, report.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Normalizes the current date to YYYY-MM-DD format.
     *
     * @return The current date in YYYY-MM-DD format.
     */
    private static String getCurrentDate()
    {
    	LocalDateTime current = LocalDateTime.now();
    	String output = current.toString();
    	output = output.substring(0, output.indexOf('T'));
    	return output;
    }

}
