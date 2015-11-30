package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import phonebook.viewcontroller.ContactListTableModel;

/**
 * action class for the "save" action 
 */
public class SaveContactsToCurrentFileAction extends SaveContactsAction {

	public SaveContactsToCurrentFileAction(ContactListTableModel model, JFrame frame) {
		super(model, frame, "Save");
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			model.saveContactsToPath(model.getCurrentFilePath());
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(frame, "error opening file");
		}
	}
	
	
}
