package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import phonebook.viewcontroller.ContactListTableModel;

/*
 * The action that performs loading the contact list
 * Buttons and menu items all use this action
 */
public class LoadContactsAction extends AbstractAction {
	
	private static final String ERROR_OPENING_FILE_MESSAGE = "Error opening file ";
	private static final String LOAD_ACTION_NAME = "Open";
	
	private ContactListTableModel model;
	private JFrame frame;

	public LoadContactsAction(ContactListTableModel model, JFrame frame) {
		super(LOAD_ACTION_NAME);
		this.model = model;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				model.loadContactsFromPath(file.toPath());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(frame, ERROR_OPENING_FILE_MESSAGE + file.toPath());
			}
		}
	}

}
