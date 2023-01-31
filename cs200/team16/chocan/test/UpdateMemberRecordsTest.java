package cs200.team16.chocan.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cs200.team16.chocan.Address;
import cs200.team16.chocan.MemberRecord;
import cs200.team16.chocan.RecordController;

/**
 * Tests the RecordController & adjacent classes with JUnit.
 * 
 * @author Kaitlyn NeSmith
 */
public class UpdateMemberRecordsTest {
	
	// Because the RecordController class is -- in essence -- static,
	// we don't need to have a @Before method for it.
	Address address = new Address("308 Negra Arroyo Lane", "Albuquerque", "NM", "87104");
	
	@Test
	public void testGetMember() throws Exception {
		// We shouldn't have this member beforehand.
		assertTrue(RecordController.getMember(123456789) == null);
		
		// Add a member.
		MemberRecord member = new MemberRecord("Walter White", 123456789, address);
		RecordController.addMember(member);
		
		// We should now have this member!
		assertTrue(RecordController.getMember(123456789) != null);
	}
	
	@Test
	public void testDeleteMember() throws Exception {
		// Add a member first
		MemberRecord member = new MemberRecord("Walter White", 123456789, address);
		RecordController.addMember(member);
		
		// Remove the member.
		RecordController.deleteMember(123456789);
		
		// We should no longer have that member.
		assertTrue(RecordController.getMember(123456789) == null);
	}
	
	@Test
	public void testSetMemberName() throws Exception {
		// Add a member first
		MemberRecord member = new MemberRecord("Skyler White", 123456789, address);
		RecordController.addMember(member);
		
		// Oh no! We need to change their name.
		// Get the member first.
		MemberRecord gottenMember = RecordController.getMember(123456789);
		// Then, use mdreis' setName() function.
		gottenMember.setName("Walter White");
		
		// Now, test if it worked!
		assertTrue(gottenMember.getName().equals("Walter White"));
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		// In case we have a residual member, we delete them.
		// This occurs because of the RecordController's static nature,
		// combined with the random run order of JUnit.
		if (RecordController.getMember(123456789) != null)
			RecordController.deleteMember(123456789);
	}
	
	// Whoops! This test was irrelevant, but I'm leaving it here as
	// an example to those who need something to work off of :)
	/*@Test
	public void testGetWeeklyServiceRecords() throws Exception {
		// Create and add some records.
		ServiceRecord service1 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), null, null, null, "Service Record 1");
		ServiceRecord service2 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), null, null, null, "Service Record 2");
		ServiceRecord service3 = new ServiceRecord(LocalDateTime.MIN, LocalDateTime.now(), null, null, null, "Service Record 3");
		ServiceRecord service4 = new ServiceRecord(LocalDateTime.MIN, LocalDateTime.now(), null, null, null, "Service Record 4");
		ServiceRecord service5 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), null, null, null, "Service Record 5");
		RecordController.addServiceRecord(service1);
		RecordController.addServiceRecord(service2);
		RecordController.addServiceRecord(service3);
		RecordController.addServiceRecord(service4);
		RecordController.addServiceRecord(service5);
		
		// Get the weekly service records.
		// Should only be 1, 2, 5.
		// First, test get weekly; we should only have three records.
		ServiceRecord[] services = RecordController.getWeeklyServiceRecords();
		assertTrue(services.length == 3);
		
		// Then, doubly test Faruq's getComments() function.
		assertTrue(services[0].getComments().equals("Service Record 1"));
		assertTrue(services[1].getComments().equals("Service Record 2"));
		assertTrue(services[2].getComments().equals("Service Record 5"));
	}*/

}
