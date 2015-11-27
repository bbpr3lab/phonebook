package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import phonebook.viewcontroller.ContactListTableModel;
import phonebook.viewcontroller.MainFrame;

/*
 * action class to perform deletion of the selection contact
 */
public class DeleteContactAction extends AbstractAction {
	
	private ContactListTableModel model;
	private MainFrame frame;

	public DeleteContactAction(ContactListTableModel model, MainFrame frame) {
		super("Delete", UIManager.getIcon("InternalFrame.closeIcon"));
		this.model = model;
		this.frame = frame;
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.deleteSelectedContact();
	}

}
