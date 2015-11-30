package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.UIManager;

import phonebook.viewcontroller.ContactListTableModel;
import phonebook.viewcontroller.MainFrame;

/**
 * action to create a blank contact list
 */
public class NewContactListAction extends AbstractAction {


	private MainFrame frame;
	private ContactListTableModel model;

	public NewContactListAction(ContactListTableModel model, MainFrame frame) {
		super("New", UIManager.getIcon("FileView.fileIcon"));
		this.frame = frame;
		this.model = model;
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.newContactList();
	}

}
