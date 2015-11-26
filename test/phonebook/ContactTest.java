package phonebook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import phonebook.validation.ContactValidator;

public class ContactTest {
	
	Contact contact;
	ContactValidator validator = new ContactValidator();

	@Before
	public void seuUp() throws Exception {
		contact = new Contact();
	}

	@Test
	public void testValidatorCatchesInvalidContact() {
		contact.setCellNumber("not a valid number");
		contact.setWorkEmail("not a valid email");
		assertFalse("invalid contact should be caught", validator.validate(contact));
	}
	
	@Test
	public void testValidatorValidatesValidContact() {
		contact.setFirstname("foo");
		contact.setLastname("bar");
		contact.setCellNumber("11-22-333-444");
		contact.setWorkEmail("email@example.com");
		assertTrue("valid contact should be validated", validator.validate(contact));
	}
	
	@Test
	public void testNullNameIsInvalid() {
		assertFalse("null name should be invalid", validator.validate(contact));
	}

}
