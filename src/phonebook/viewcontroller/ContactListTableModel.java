package phonebook.viewcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.table.AbstractTableModel;

import phonebook.model.Contact;
import phonebook.model.ContactList;
import phonebook.model.ContactListSerializer;

public class ContactListTableModel extends AbstractTableModel {

	private ContactListSerializer serializer;
	private ContactList contactList;
	
	private Path currentFilePath;
	private boolean contactListDirty;
	
	public ContactListTableModel() {
		serializer = new ContactListSerializer();
		contactList = new ContactList();
		contactListDirty = false;
	}
	
	@Override
	public int getRowCount() {
		return contactList.numberOfContacts();
	}

	@Override
	public int getColumnCount() {
		return Contact.NUMBER_OF_PROPERTIES;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Contact contact = contactList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return contact.getFirstname();
		case 1:
			return contact.getLastname();
		case 2:
			return contact.getHomeNumber();
		case 3:
			return contact.getWorkNumber();
		case 4:
			return contact.getCellNumber();
		case 5:
			return contact.getWorkEmail();
		case 6:
			return contact.getPersonalEmail();
		}
		return null;
	}
	
	public void loadContactsFromPath(Path path) throws ClassNotFoundException, IOException {
		contactList = serializer.load(path);
		currentFilePath = path;
		fireTableDataChanged();
	}
	
	public void saveContactsToPath(Path path) throws FileNotFoundException, IOException {
		serializer.save(path, contactList);
		contactListDirty = false;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "firstname";
		case 1:
			return "lastname";
		case 2:
			return "home number";
		case 3:
			return "work number";
		case 4:
			return "cell number";
		case 5:
			return "work email";
		case 6:
			return "personal email";
		}
		return "unknown";
	}
	
	
	
	public Path getCurrentFilePath() {
		return currentFilePath;
	}
}
