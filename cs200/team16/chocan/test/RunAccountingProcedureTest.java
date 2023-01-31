package cs200.team16.chocan.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import cs200.team16.chocan.ReportController;
import cs200.team16.chocan.ServiceRecord;
import cs200.team16.chocan.RecordController;
import cs200.team16.chocan.Address;
import cs200.team16.chocan.MemberRecord;
import cs200.team16.chocan.ProviderRecord;

public class RunAccountingProcedureTest {
	
	
	private Address testAddress = new Address("123 Main Street", "Clarksville", "TN", "37043");
	private MemberRecord member;
	private ProviderRecord provider;
	
	@BeforeAll
	public void setUp() throws Exception {
		//Create a member record
		member = new MemberRecord("Tim Weah", 123456789, testAddress);
		provider = new ProviderRecord("Christian Pulisic", 234567890, testAddress, 9999);
		RecordController.addMember(member);
	}
	@Test
	public void testGetWeeklyServiceRecords() throws Exception {
		//Create some service records
		ServiceRecord service1 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), null, null, null, "Service Record 1");
		ServiceRecord service2 = new ServiceRecord(LocalDateTime.MIN, LocalDateTime.now(), null, null, null, "Service Record 2");
		ServiceRecord service3 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), null, null, null, "Service Record 3");
		ServiceRecord service4 = new ServiceRecord(LocalDateTime.MIN, LocalDateTime.now(), null, null, null, "Service Record 4");
		
		RecordController.addServiceRecord(service1);
		RecordController.addServiceRecord(service2);
		RecordController.addServiceRecord(service3);
		RecordController.addServiceRecord(service4);

		//Get the services records for the week (should be 1 and 3)
		ServiceRecord[] services = RecordController.getWeeklyServiceRecords();
		assertTrue(services.length == 2);
		
		assertTrue(services[0].getComments().equals("Service Record 1"));
		assertTrue(services[1].getComments().equals("Service Record 3"));
	}
	
	@Test
	public void testGenerateMemberReport(){
		//Create a service record
		ServiceRecord service1 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), provider, member, null, "Assisting in goals making");
		assertTrue(ReportController.generateMemberReport(123456789).name.equals("Tim Weah"));
		assertTrue(ReportController.generateMemberReport(123456789).accountNumber.equals(123456789));
		assertTrue(ReportController.generateMemberReport(123456789).address.getStreetAddress().equals(testAddress.getStreetAddress()));
		assertTrue(ReportController.generateMemberReport(123456789).address.getCity().equals(testAddress.getCity()));
		assertTrue(ReportController.generateMemberReport(123456789).address.getState().equals(testAddress.getState()));
		assertTrue(ReportController.generateMemberReport(123456789).address.getZipCode().equals(testAddress.getZipCode()));
		
	}
	
	@Test
	public void testGenerateEFTReport() {
		//Create a provider record to make EFT report from
		ProviderRecord provider = new ProviderRecord("Sawyer Griffy", 123456789, testAddress, 9998);
		assertTrue(ReportController.generateEFT(123456789).name.equals("Sawyer Griffy"));
		assertTrue(ReportController.generateEFT(123456789).accountNumber.equals(123456789));
	}

}
