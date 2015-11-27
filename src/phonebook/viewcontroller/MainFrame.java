package phonebook.viewcontroller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import phonebook.viewcontroller.actions.LoadContactsAction;
import phonebook.viewcontroller.actions.SaveContactsAction;
import phonebook.viewcontroller.actions.SaveContactsToCurrentFileAction;


public class MainFrame extends JFrame {
	
	private static final String MAIN_FRAME_TITLE = "Phonebook";
	private static final Dimension INITIAL_DIMENSION = new Dimension(800, 400);
	
	private ContactListTableModel contactListTableModel;
	
	private LoadContactsAction loadContactsAction;
	private SaveContactsAction saveContactsAction;
	private SaveContactsToCurrentFileAction saveContactsToCurrentFileAction;
	
	private class MainWindowAdapter extends WindowAdapter {
		private static final String CONFIRM_EXIT_MESSAGE =
				"Are you sure you want to exit?";
		
		@Override
		public void windowClosing(WindowEvent e) {
			int result = JOptionPane.showConfirmDialog(MainFrame.this, CONFIRM_EXIT_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				MainFrame.this.dispose();
			}
		}
	}

	public MainFrame() {
		super(MAIN_FRAME_TITLE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setMinimumSize(INITIAL_DIMENSION);
		setLayout(new BorderLayout());
		
		addWindowListener(new MainWindowAdapter());
				
		contactListTableModel = new ContactListTableModel();
		
		loadContactsAction = new LoadContactsAction(contactListTableModel, this);
		saveContactsAction = new SaveContactsAction(contactListTableModel, this, "Save as");
		saveContactsToCurrentFileAction = new SaveContactsToCurrentFileAction(contactListTableModel, this);
		
		JTable table = new JTable(contactListTableModel);
		table.setFillsViewportHeight(true);
		JScrollPane sp = new JScrollPane(table);		
		getContentPane().add(sp, BorderLayout.CENTER); 
		
		JButton loadButton = new JButton(loadContactsAction);
		JButton saveAsButton = new JButton(saveContactsAction);
		JButton saveButton = new JButton(saveContactsToCurrentFileAction);
		JPanel southPanel = new JPanel();
		southPanel.add(loadButton);
		southPanel.add(saveButton);
		southPanel.add(saveAsButton);
		getContentPane().add(southPanel, BorderLayout.SOUTH);

	}
}
