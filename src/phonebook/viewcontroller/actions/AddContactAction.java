package phonebook.viewcontroller.actions;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import phonebook.model.Contact;
import phonebook.viewcontroller.ContactListTableModel;

/*
 * action to add a new contact to the open contact list
 */
public class AddContactAction extends AbstractAction {
	
	private JFrame frame;
	private ContactListTableModel model;

	public AddContactAction(ContactListTableModel model, JFrame frame) {
		super("Add");
		this.model = model;
		this.frame = frame;
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new AddContactDialog().setVisible(true);
	}
	
	private class AddContactDialog extends JDialog {
		
		private static final int FIELDLEN = 20;

		JTextField firstnameField = new JTextField(FIELDLEN);
		JTextField lastnameField = new JTextField(FIELDLEN);
		JTextField workEmailField = new JTextField(FIELDLEN);
		JTextField personalEmailField = new JTextField(FIELDLEN);
		JTextField homeNumberField = new JTextField(FIELDLEN);
		JTextField workNumberField = new JTextField(FIELDLEN);
		JTextField cellNumberField = new JTextField(FIELDLEN);
		
		public AddContactDialog() {
			setMinimumSize(new Dimension(300, 400));
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			Container cp = getContentPane();
			
			JButton addButton = new JButton("add");
			JButton cancelButton = new JButton("cancel");
			
			addButton.addActionListener(e -> {
				Contact contact = new phonebook.model.Contact();
				contact.setFirstname(firstnameField.getText());
				contact.setLastname(lastnameField.getText());
				contact.setWorkEmail(workEmailField.getText());
				contact.setPersonalEmail(personalEmailField.getText());
				contact.setHomeNumber(homeNumberField.getText());
				contact.setWorkNumber(workNumberField.getText());
				contact.setCellNumber(cellNumberField.getText());;
				try {
					model.addContact(contact);
					dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "contact info invalid");
				}
			});
			
			cancelButton.addActionListener(e -> {
				dispose();
			});
			
			JLabel firstnameLabel = new JLabel("*firstname: ");
			JLabel lastnameLabel = new JLabel("lastname: ");
			JLabel workEmailLabel = new JLabel("work email: ");
			JLabel personalEmailLabel = new JLabel("personal email: ");
			JLabel homeNumberLabel = new JLabel("home number: ");
			JLabel workNumberLabel = new JLabel("work number: ");
			JLabel cellNumberLabel = new JLabel("cell number: ");
			
			JPanel panel = new JPanel();
			panel.add(firstnameLabel);
			panel.add(firstnameField);
			panel.add(lastnameLabel);
			panel.add(lastnameField);
			panel.add(workEmailLabel);
			panel.add(workEmailField);
			panel.add(personalEmailLabel);
			panel.add(personalEmailField);
			panel.add(homeNumberLabel);
			panel.add(homeNumberField);
			panel.add(workNumberLabel);
			panel.add(workNumberField);
			panel.add(cellNumberLabel);
			panel.add(cellNumberField);
			cp.add(panel, BorderLayout.CENTER);
			
			panel = new JPanel();
			panel.add(addButton);
			panel.add(cancelButton);
			cp.add(panel, BorderLayout.SOUTH);
		}

		
	}

}
