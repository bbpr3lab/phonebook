package phonebook;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import phonebook.validation.InvalidContactException;

public class ContactListTest {
	
	ContactList contactList;
	Contact validContact;
	Contact invalidContact;

	@Before
	public void setup() throws Exception {
		contactList = new ContactList();
		
		invalidContact = new Contact();
		validContact = new Contact();
		validContact.setFirstname("firstname");
		validContact.setLastname("lastname");
		validContact.setCellNumber("11-222-333");
		validContact.setWorkEmail("email@example.com");
	}

	@Test(expected = InvalidContactException.class)
	public void testAddingInvalidContact() throws InvalidContactException {
		contactList.addContact(invalidContact);
	}
	
	@Test
	public void testAddingValidContact() throws InvalidContactException {
		contactList.addContact(validContact);
		assertEquals("valid contact not added to contactlist",
				1, contactList.numberOfContacts());
	}
	
	@Test
	public void testRemovingContact() throws InvalidContactException {
		contactList.addContact(validContact);
		contactList.removeContact(validContact);
		assertEquals("contactlist should be empty after removing last contact",
				0, contactList.numberOfContacts());
	}

}
