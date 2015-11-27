package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import phonebook.viewcontroller.ContactListTableModel;
import phonebook.viewcontroller.MainFrame;

public class NewContactListAction extends AbstractAction {


	private MainFrame frame;
	private ContactListTableModel model;

	public NewContactListAction(ContactListTableModel model, MainFrame frame) {
		super("New");
		this.frame = frame;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		model.newContactList();
	}

}
