package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import phonebook.viewcontroller.ContactListTableModel;

/*
 * the action that performs saving the contact list
 */
public class SaveContactsAction extends AbstractAction {
	
	private static final String ERROR_SAVING_CONTACTS_MESSAGE =
			"Error saving contacts to file ";
	
	private JFrame frame;
	protected ContactListTableModel model;

	public SaveContactsAction(ContactListTableModel model, JFrame frame, String name) {
		super(name);
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				model.saveContactsToPath(file.toPath());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(frame, ERROR_SAVING_CONTACTS_MESSAGE + file.toPath());
			}
		}
	}

}
