package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import phonebook.viewcontroller.ContactListTableModel;

/*
 * action class for the "save" action 
 */
public class SaveContactsToCurrentFileAction extends SaveContactsAction {

	public SaveContactsToCurrentFileAction(ContactListTableModel model, JFrame frame) {
		super(model, frame, "Save");
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see phonebook.viewcontroller.actions.SaveContactsAction#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			model.saveContactsToPath(model.getCurrentFilePath());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
