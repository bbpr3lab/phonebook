package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import phonebook.viewcontroller.ContactListTableModel;
import phonebook.viewcontroller.MainFrame;

/*
 * action class to perform deletion of the selection contact
 */
public class DeleteContactAction extends AbstractAction {
	
	private ContactListTableModel model;
	private MainFrame frame;

	public DeleteContactAction(ContactListTableModel model, MainFrame frame) {
		super("Delete");
		this.model = model;
		this.frame = frame;
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.deleteSelectedContact();
	}

}
