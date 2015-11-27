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

import phonebook.viewcontroller.actions.AddContactAction;
import phonebook.viewcontroller.actions.LoadContactsAction;
import phonebook.viewcontroller.actions.NewContactListAction;
import phonebook.viewcontroller.actions.SaveContactsAction;
import phonebook.viewcontroller.actions.SaveContactsToCurrentFileAction;


public class MainFrame extends JFrame {
	
	private static final String MAIN_FRAME_TITLE = "Phonebook";
	private static final Dimension INITIAL_DIMENSION = new Dimension(800, 400);
	
	private ContactListTableModel contactListTableModel;
	
	private LoadContactsAction loadContactsAction;
	private SaveContactsAction saveContactsAction;
	private SaveContactsToCurrentFileAction saveContactsToCurrentFileAction;
	private AddContactAction addContactAction;
	private NewContactListAction newContactListAction;
	
	private class MainWindowAdapter extends WindowAdapter {
		private static final String CONFIRM_EXIT_MESSAGE =
				"Are you sure you want to exit?";
		
		@Override
		public void windowClosing(WindowEvent e) {
			DirtyCheckResult result = dirtyCheck();
			if (result == DirtyCheckResult.NOT_DIRTY || result == DirtyCheckResult.NO_SAVE) {
				dispose();
			}
		}
	}

	public MainFrame() {
		super(MAIN_FRAME_TITLE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setMinimumSize(INITIAL_DIMENSION);
		setLayout(new BorderLayout());
		
		addWindowListener(new MainWindowAdapter());
				
		contactListTableModel = new ContactListTableModel(this);
		
		loadContactsAction = new LoadContactsAction(contactListTableModel, this);
		saveContactsAction = new SaveContactsAction(contactListTableModel, this, "Save as");
		saveContactsToCurrentFileAction = new SaveContactsToCurrentFileAction(contactListTableModel, this);
		addContactAction = new AddContactAction(contactListTableModel, this);
		newContactListAction = new NewContactListAction(contactListTableModel, this);

		JTable table = new JTable(contactListTableModel);
		table.setFillsViewportHeight(true);
		JScrollPane sp = new JScrollPane(table);		
		getContentPane().add(sp, BorderLayout.CENTER);
		
		JButton loadButton = new JButton(loadContactsAction);
		JButton saveAsButton = new JButton(saveContactsAction);
		JButton saveButton = new JButton(saveContactsToCurrentFileAction);
		JButton addContactButton = new JButton(addContactAction);
		JButton newContactListButton = new JButton(newContactListAction);
		JPanel southPanel = new JPanel();
		southPanel.add(loadButton);
		southPanel.add(saveButton);
		southPanel.add(saveAsButton);
		southPanel.add(addContactButton);
		southPanel.add(newContactListButton);
		getContentPane().add(southPanel, BorderLayout.SOUTH);

	}
	
	private static final String DIRTY_MODEL_MESSAGE =
			"You have unsaved changes. Save now?";
	
	public static enum DirtyCheckResult { SAVE, NO_SAVE, CANCEL, NOT_DIRTY }
	
	public DirtyCheckResult dirtyCheck() {
		if (contactListTableModel.isDirty()) {
			int result = JOptionPane.showConfirmDialog(this, DIRTY_MODEL_MESSAGE);
			switch (result) {
			case JOptionPane.CANCEL_OPTION:
				return DirtyCheckResult.CANCEL;
			case JOptionPane.NO_OPTION:
				return DirtyCheckResult.NO_SAVE;
			case JOptionPane.YES_OPTION:
				boolean currentFilepathValid = contactListTableModel.isCurrentFilepathValid();
				if (currentFilepathValid) {
					saveContactsToCurrentFileAction.actionPerformed(null);
				} else {
					saveContactsAction.actionPerformed(null);
				}
				return DirtyCheckResult.SAVE;
			}
		}
		return DirtyCheckResult.NOT_DIRTY;
	}

	public void disableSaveAction() {
		saveContactsToCurrentFileAction.setEnabled(false);
	}
	
	public void enableSaveAction() {
		saveContactsToCurrentFileAction.setEnabled(true);
	}
}
