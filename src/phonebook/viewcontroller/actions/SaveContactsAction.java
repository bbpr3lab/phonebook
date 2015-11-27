package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import phonebook.viewcontroller.ContactListTableModel;

/*
 * action class to perform the "save as" action
 */
public class SaveContactsAction extends AbstractAction {
	
	private static final String ERROR_SAVING_CONTACTS_MESSAGE =
			"Error saving contacts to file ";
	
	private JFrame frame;
	protected ContactListTableModel model;

	public SaveContactsAction(ContactListTableModel model, JFrame frame, String name) {
		super(name, UIManager.getIcon("FileView.floppyDriveIcon"));
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
