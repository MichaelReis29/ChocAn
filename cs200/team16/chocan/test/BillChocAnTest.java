package cs200.team16.chocan.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cs200.team16.chocan.Address;
import cs200.team16.chocan.MemberRecord;
import cs200.team16.chocan.ProviderDirectory;
import cs200.team16.chocan.ProviderDirectoryEntry;
import cs200.team16.chocan.ProviderRecord;
import cs200.team16.chocan.RecordController;
import cs200.team16.chocan.ServiceRecord;

/**
 * Tests the RecordController & adjacent classes with JUnit.
 *
 * @author Faruq Yusuff
 */
public class BillChocAnTest {
	private static ServiceRecord serviceRecord;
	private static int oldConsultationCount;
	private static int oldWeeklyFee;
	
	@BeforeAll
	public static void beforeAll() {
		ProviderRecord providerRecord = new ProviderRecord("Provider", 3333, null, 3333);
	    RecordController.addProvider(providerRecord);
	    
	    Address address = new Address("1 Hacker Way", "Menlo Park", "California", "94025");
	    MemberRecord memberRecord = new MemberRecord("John Doe", 4444, address);
	    RecordController.addMember(memberRecord);
	    
		ProviderDirectoryEntry serviceProvided = ProviderDirectory.searchByCode("4001");
		oldConsultationCount = providerRecord.getConsultationCount();
		oldWeeklyFee = providerRecord.getWeeklyFee();
				
		serviceRecord = new ServiceRecord(
				LocalDateTime.now(),
				LocalDateTime.now(),
				providerRecord,
				memberRecord,
				serviceProvided,
				"N/A"
		);
		RecordController.addServiceRecord(serviceRecord);
	}
	
	@Test
	public void testAddServiceRecord() {
		ServiceRecord retrievedServiceRecord = RecordController.getServiceRecordByID(serviceRecord.getID());
		assertTrue(retrievedServiceRecord != null);
		
		assertTrue(retrievedServiceRecord == serviceRecord);
	}
	
	@Test
	public void testGetConsulationCount() {
		int newConsultationCount = serviceRecord.getProvider().getConsultationCount();
		assertTrue((newConsultationCount-oldConsultationCount) == 1);
	}
	
	@Test
	public void testGetWeeklyFee() {
		int increaseInWeeklyFee = serviceRecord.getProvider().getWeeklyFee() - oldWeeklyFee;
		assertTrue(increaseInWeeklyFee == serviceRecord.getServiceProvided().getServiceFee());
	}
	
	@AfterAll
	public static void teardown() {
		RecordController.deleteMember(serviceRecord.getMember().getAccountNumber());
		RecordController.deleteProvider(serviceRecord.getProvider().getAccountNumber());
		RecordController.deleteServiceRecord(serviceRecord.getID());
	}

}
