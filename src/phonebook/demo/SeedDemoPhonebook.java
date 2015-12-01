package phonebook.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

import phonebook.model.Contact;
import phonebook.model.ContactList;
import phonebook.model.ContactListSerializer;
import phonebook.model.validation.InvalidContactException;

public class SeedDemoPhonebook {
	
	static final Random random = new Random();

	static final String firstnames[] = {
		"foo", "bar", "baz", "ack", "quux"
	};
	
	static final String lastnames[] = {
		"qwe", "asd", "zxc", "rty", "fgh", "cvb"
	};
	
	static final String domains[] = {
		"example.com", "email.com"
	};
	
	static String randomPhoneNumber() {
		
		if (random.nextInt(100) == 0)
			return null;
		
		String phone = "";
		
		int randomNum = random.nextInt(9);
		phone += randomNum;
		phone += randomNum;
		phone += "-";
		
		randomNum = random.nextInt(9);
		phone += randomNum;
		phone += randomNum;
		phone += randomNum;
		phone += "-";
		
		randomNum = random.nextInt(9);
		phone += randomNum;
		phone += randomNum;
		phone += randomNum;
		
		return phone;
	}
	
	static String randomEmail(String firstname, String lastname) {
		if (random.nextInt(100) == 0) {
			return null;
		}
		
		String email = firstname + "." + lastname + random.nextInt(99) + "@"
				+ domains[random.nextInt(2)];

		return email;
	}
	
	static Contact randomContact() {
		Contact contact = new Contact();
		
		contact.setFirstname(firstnames[random.nextInt(firstnames.length)]);
		contact.setLastname(lastnames[random.nextInt(lastnames.length)]);
		contact.setWorkEmail(randomEmail(contact.getFirstname(), contact.getLastname()));
		contact.setPersonalEmail(randomEmail(contact.getFirstname(), contact.getLastname()));
		contact.setCellNumber(randomPhoneNumber());
		contact.setHomeNumber(randomPhoneNumber());
		contact.setWorkNumber(randomPhoneNumber());
		
		return contact; 
	}
	
	public static void main(String... args) throws InvalidContactException, FileNotFoundException, IOException {
		ContactList contactList = new ContactList();
		for (int i = 0; i < 1000; i++) {
			contactList.addContact(randomContact());
		}
		ContactListSerializer serializer = new ContactListSerializer();
		serializer.save(Paths.get("bigdatatest.db"), contactList);
	}
}
