package phonebook;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ContactListSerializerTest {
	
	ContactList listToBeSaved;
	ContactList listToBeLoaded;
	
	static ContactListSerializer serializer;
	
	static final String testFileName = "testdata.dat";
	
	static List<Contact> testContacts = new ArrayList<>();
	
	@BeforeClass
	public static void classSetup() {
		
		Contact contact;
		
		String[] names = {"foo", "bar", "baz"};
		String[] emails = {"foo@example.com", "bar@example.com", "baz@example.com" };
		String[] numbers = {"111-222-333", "444-555-666", "777-888-999" };
		
		for (int i = 0; i < names.length; i++) {
			contact = new Contact();
			contact.setFirstname(names[i]);
			contact.setWorkEmail(emails[i]);
			contact.setCellNumber(numbers[i]);
			testContacts.add(contact);
		}
		
		serializer = new ContactListSerializer();
	}
	
	@Before
	public void setup() throws Exception {
		listToBeSaved = new ContactList();
		for (Contact contact : testContacts) {
			listToBeSaved.addContact(contact);
		}
	}

	@Test
	public void testSavingContactList() throws FileNotFoundException, IOException, ClassNotFoundException {
		serializer.save(Paths.get(testFileName), listToBeSaved);
		listToBeLoaded = serializer.load(Paths.get(testFileName));
		assertEquals("loaded list should contain the same number of elements as the one saved",
				3, listToBeLoaded.numberOfContacts());
	}

}
