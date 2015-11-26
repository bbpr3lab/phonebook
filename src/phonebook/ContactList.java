package phonebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phonebook.validation.ContactValidator;
import phonebook.validation.InvalidContactException;

public class ContactList implements Serializable {

	private static final long serialVersionUID = 6280555241805470080L;
	
	transient private ContactValidator validator;
	private List<Contact> contacts;
	
	/*
	 * constructor for ContactList
	 */
	public ContactList() {
		contacts = new ArrayList<Contact>();
		validator = new ContactValidator();
	}
	
	/*
	 * add contact to list of contacts
	 * @param contact the contact to be added
	 * @throws InvalidContactException if the contact is not valid
	 */
	public void addContact(Contact contact) throws InvalidContactException {
		if (!validator.validate(contact)) {
			throw new InvalidContactException();
		}
		contacts.add(contact);
	}
	
	/*
	 * @return the number of contacts in the contactlist
	 */
	public int numberOfContacts() {
		return contacts.size();
	}
	
	/*
	 * @param contact the contact to be removed
	 */
	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}
	
	/*
	 * @param contact the contact to find
	 * @return true, if contains the contact
	 */
	public boolean contains(Contact contact) {
		return contacts.contains(contact);
	}
	
	/*
	 * finds all contacts matching the given name
	 * @param name the name to search for
	 * @return list containing the matching contacts
	 */
	public List<Contact> findByName(String name) {
		String pattern = "^.*" + name.toLowerCase() + ".*$";
		List<Contact> filteredContacts = new ArrayList<>();
		for (Contact contact : contacts) {
			String firstname = contact.getFirstname();
			String lastname = contact.getLastname();
			if (firstname.matches(pattern) || (lastname != null && lastname.matches(pattern)))
				filteredContacts.add(contact);
		}
		return filteredContacts;
	}
}
