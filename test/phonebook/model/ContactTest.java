package phonebook.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import phonebook.model.Contact;
import phonebook.model.validation.ContactValidator;

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
	
	@Test
	public void testContactEquals() {
		Contact c1 = new Contact(), c2 = new Contact();
		c1.setFirstname("foo");
		c2.setFirstname("foo");
		c1.setCellNumber("11-22-33");
		c2.setCellNumber("11-22-33");
		c1.setWorkEmail("email@example.com");
		c2.setWorkEmail("email@example.com");
		assertEquals("contacts with the same data should be equal", c1, c2);
		c1.setWorkEmail("c1@example.com");
		assertNotEquals("contacts with different data should not be equal", c1, c2);
	}

}
