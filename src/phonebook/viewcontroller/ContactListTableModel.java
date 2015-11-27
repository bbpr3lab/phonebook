package phonebook.viewcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import phonebook.model.Contact;
import phonebook.model.ContactList;
import phonebook.model.ContactListSerializer;
import phonebook.model.validation.InvalidContactException;
import phonebook.viewcontroller.MainFrame.DirtyCheckResult;

public class ContactListTableModel extends AbstractTableModel {

	private ContactListSerializer serializer;
	private ContactList contactList;
	
	/*
	 * tracking the current file for the save action
	 */
	private Path currentFilePath;
	/*
	 * tracking whether the contact list has been edited since last save
	 */
	private boolean contactListDirty;
	
	private MainFrame frame;
	
	public ContactListTableModel(MainFrame frame) {
		serializer = new ContactListSerializer();
		contactList = new ContactList();
		contactListDirty = false;
		this.frame = frame; 
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
		setCurrentFilepath(path);
		contactListDirty = false;
		fireTableDataChanged();
	}
	
	public void saveContactsToPath(Path path) throws FileNotFoundException, IOException {
		serializer.save(path, contactList);
		contactListDirty = false;
		setCurrentFilepath(path);
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
	
	/*
	 * TODO: fix this...
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Contact oldContact = contactList.get(rowIndex);
		Contact contact = new Contact(oldContact);
		String str = (String) value;
		switch (columnIndex) {
		case 0:
			contact.setFirstname(str);
			break;
		case 1:
			contact.setLastname(str);
			break;
		case 2:
			contact.setHomeNumber(str);
			break;
		case 3:
			contact.setWorkNumber(str);
			break;
		case 4:
			contact.setCellNumber(str);
			break;
		case 5:
			contact.setWorkEmail(str);
			break;
		case 6:
			contact.setPersonalEmail(str);
			break;
		}
		boolean contactValid = contactList.validate(contact);
		if (contactValid) {
			oldContact.merge(contact);
			contactListDirty = true;
		} else {
			JOptionPane.showMessageDialog(frame, "invalid field", "error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public Path getCurrentFilePath() {
		return currentFilePath;
	}
	
	public boolean isCurrentFilepathValid() {
		return currentFilePath != null;
	}
	
	public void addContact(Contact contact) throws InvalidContactException {
		contactList.addContact(contact);
		contactListDirty = true;
		fireTableDataChanged();
	}
	
	public boolean isDirty() {
		return contactListDirty;
	}
	
	public void newContactList() {
		DirtyCheckResult result = frame.dirtyCheck();
		if (result == DirtyCheckResult.CANCEL || result == DirtyCheckResult.SAVE)
			return;
		
		contactList = new ContactList();
		contactListDirty = false;
		frame.disableSaveAction();
		fireTableDataChanged();
	}
	
	private void setCurrentFilepath(Path path) {
		this.currentFilePath = path;
		frame.enableSaveAction();
	}
}
