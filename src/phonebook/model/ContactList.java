package phonebook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phonebook.model.validation.ContactValidator;
import phonebook.model.validation.InvalidContactException;

/**
 * container class for storing Contact objects
 *
 */
public class ContactList implements Serializable {

	private static final long serialVersionUID = -1916049214286413720L;
	
	/**
	 * object used to validate the contacts before adding them to the list
	 */
	static final private ContactValidator validator = new ContactValidator();
	
	/**
	 * the contacts stored in a list object
	 */
	private List<Contact> contacts;
	
	/**
	 * constructor for ContactList
	 */
	public ContactList() {
		contacts = new ArrayList<Contact>();
	}
	
	/**
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
	
	/**
	 * @return the number of contacts in the contactlist
	 */
	public int numberOfContacts() {
		return contacts.size();
	}
	
	/**
	 * @param contact the contact to be removed
	 */
	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}
	
	/**
	 * @param contact the contact to find
	 * @return true, if contains the contact
	 */
	public boolean contains(Contact contact) {
		return contacts.contains(contact);
	}
	
	/**
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
	
	/**
	 * get the contact at a given index
	 * @param index the index
	 * @return the contact
	 */
	public Contact get(int index) {
		return contacts.get(index);
	}
	
	/**
	 * validate a contact with the validator object
	 * @param contact the contact
	 * @return true if the contact is valid according to the validator
	 */
	public boolean validate(Contact contact) {
		return validator.validate(contact);
	}
}
