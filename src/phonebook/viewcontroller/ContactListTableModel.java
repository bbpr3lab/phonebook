package phonebook.viewcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import phonebook.model.Contact;
import phonebook.model.ContactList;
import phonebook.model.ContactListSerializer;
import phonebook.model.validation.InvalidContactException;
import phonebook.viewcontroller.MainFrame.DirtyCheckResult;

public class ContactListTableModel extends AbstractTableModel {

	private ContactListSerializer serializer;
	private ContactList contactList;
	private JTable table;
	
	
	private static final String[] columnNames = {
		"firstname", "lastname", "home number", "work number", "cell number",
		"work email", "personal email"
	};
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
	
	
	/*
	 * load the contact list from a file
	 * 
	 * @param path the file to read from
	 */
	public void loadContactsFromPath(Path path) throws ClassNotFoundException, IOException {
		contactList = serializer.load(path);
		setCurrentFilepath(path);
		contactListDirty = false;
		fireTableDataChanged();
	}
	
	/*
	 * save the contact list to the file system
	 * 
	 * @param path the filename to save
	 */
	public void saveContactsToPath(Path path) throws FileNotFoundException, IOException {
		serializer.save(path, contactList);
		contactListDirty = false;
		setCurrentFilepath(path);
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
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
	
	/*
	 * @return the current file's path
	 */
	public Path getCurrentFilePath() {
		return currentFilePath;
	}
	
	/*
	 * used by the enabling mechanism of the save action
	 * 
	 * @return whether the current file is null or not
	 */
	public boolean isCurrentFilepathValid() {
		return currentFilePath != null;
	}
	
	/*
	 * add a contact to the contact list
	 * 
	 * @param contact the contact to add
	 */
	public void addContact(Contact contact) throws InvalidContactException {
		contactList.addContact(contact);
		contactListDirty = true;
		fireTableDataChanged();
	}
	
	public boolean isDirty() {
		return contactListDirty;
	}
	
	/*
	 * create a new contact list
	 * performs the check for dirty contacts
	 */
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
	
	/*
	 * delete the selected contact from the list
	 */
	public void deleteSelectedContact() {
		int rowIndex = table.getSelectedRow();
		Contact contact = contactList.get(rowIndex);
		contactList.removeContact(contact);
		fireTableDataChanged();
	}
	
	/*
	 * set the table property
	 * @param table
	 */
	public void setTable(JTable table) {
		this.table = table;
	}
}
