package cs200.team16.chocan.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import cs200.team16.chocan.Address;
import cs200.team16.chocan.MemberRecord;

/**
 * Tests the PersonRecord & Address classes with JUnit along with one adjacent class.
 * 
 * @author Michael Reis
 */
public class PersonRecordAddressTest {
	
	//instantiate MemberRecord to test PersonRecord because PersonRecord is abstract
	Address address = new Address("123 Sesame Street", "Columbus", "OH", "43016");
	
	@Test
	public void testPersonRecord() throws Exception {
		
		// Add the member.
		MemberRecord member = new MemberRecord("Elmo", 123456789, address);
		
		// This member's info should now be correctly stored in PersonRecord
		assertTrue(member.getName() == "Elmo");
		assertTrue(member.getAccountNumber() == 123456789);
		assertTrue(member.getAddress() == address);
	}
	
	@Test
	public void testAddress() throws Exception {
		
		assertTrue(address.getStreetAddress() == "123 Sesame Street" );
		assertTrue(address.getCity() == "Columbus");
		assertTrue(address.getState() == "OH");
		assertTrue(address.getZipCode() == "43016");
	}
	
	@Test
	public void testMemberRecord() throws Exception {
		// Add a member first
		MemberRecord member = new MemberRecord("Elmo", 123456789, address);
		
		// Oh no! We need to change their status.
		// Then, use kNeSmith' setStatus() function.
		member.setStatus("suspended");
		
		// Now, test if it worked!
		assertTrue(member.getStatus() == "suspended");
	}
	

	


}
