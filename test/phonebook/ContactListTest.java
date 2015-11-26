package phonebook;

import static org.junit.Assert.*;

import java.util.List;

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
	
	@Test
	public void testSeachingContacts() throws InvalidContactException {
		contactList.addContact(validContact);
		List<Contact> first = contactList.findByName("first");
		assertEquals(1, first.size());
		
		Contact anotherContact = new Contact();
		anotherContact.setFirstname("another first");
		contactList.addContact(anotherContact);
		
		first = contactList.findByName("first");
		assertEquals(2, first.size());
		
		contactList.removeContact(first.get(0));
		assertEquals(1, contactList.numberOfContacts());
		
		first = contactList.findByName("first");
		assertEquals(1, first.size());
		
		List<Contact> second = contactList.findByName("second");
		assertEquals(0, second.size());
	}

}
