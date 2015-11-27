package phonebook.viewcontroller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import phonebook.viewcontroller.actions.AddContactAction;
import phonebook.viewcontroller.actions.DeleteContactAction;
import phonebook.viewcontroller.actions.ExitAction;
import phonebook.viewcontroller.actions.LoadContactsAction;
import phonebook.viewcontroller.actions.NewContactListAction;
import phonebook.viewcontroller.actions.SaveContactsAction;
import phonebook.viewcontroller.actions.SaveContactsToCurrentFileAction;


public class MainFrame extends JFrame {
	
	private static final String MAIN_FRAME_TITLE = "Phonebook";
	private static final Dimension INITIAL_DIMENSION = new Dimension(800, 400);
	
	/*
	 * the model for the JTable
	 */
	private ContactListTableModel contactListTableModel;
	
	// global actions
	private LoadContactsAction loadContactsAction;
	private SaveContactsAction saveContactsAction;
	private SaveContactsToCurrentFileAction saveContactsToCurrentFileAction;
	private AddContactAction addContactAction;
	private NewContactListAction newContactListAction;
	private DeleteContactAction deleteContactAction;
	private ExitAction exitAction;
	
	private TableRowSorter<ContactListTableModel> tableSorter;
	
	
	/*
	 * inner class for window closing event
	 */
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

	/*
	 * set up components
	 */
	public MainFrame() {
		super(MAIN_FRAME_TITLE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setMinimumSize(INITIAL_DIMENSION);
		setLayout(new BorderLayout());
		
		addWindowListener(new MainWindowAdapter());
				
		contactListTableModel = new ContactListTableModel(this);
		
		setupActions();
		
		setupTable();
		
		setupButtons();
		
		setupMenu();

	}
	
	private void setupTable() {
		JTable table = new JTable(contactListTableModel);
		table.getSelectionModel().addListSelectionListener(e-> {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				setDeleteActionEnabled(!lsm.isSelectionEmpty());
		});
		table.setFillsViewportHeight(true);
		JScrollPane sp = new JScrollPane(table);		
		getContentPane().add(sp, BorderLayout.CENTER);
		contactListTableModel.setTable(table);
		
		JPopupMenu menu = new JPopupMenu();
		menu.add(new JMenuItem(addContactAction));
		menu.add(new JMenuItem(deleteContactAction));
		table.setComponentPopupMenu(menu);
		
		tableSorter = new TableRowSorter<>(contactListTableModel);
		table.setRowSorter(tableSorter);
		
	}
	
	private void setupButtons() {
		JTextField searchField = new JTextField(15);
		searchField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				setFilter();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				setFilter();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
			
			private void setFilter() {
				RowFilter<ContactListTableModel, Object> filter;
				try {
					filter = RowFilter.regexFilter(searchField.getText(), 0);
				} catch (java.util.regex.PatternSyntaxException ex) {
					return;
				}
				tableSorter.setRowFilter(filter);
			}
			
		});
		JPanel southPanel = new JPanel();
		southPanel.add(searchField);
		southPanel.add(new JButton(newContactListAction));
		southPanel.add(new JButton(loadContactsAction));
		southPanel.add(new JButton(saveContactsAction));
		southPanel.add(new JButton(saveContactsToCurrentFileAction));
		southPanel.add(new JButton(addContactAction));
		southPanel.add(new JButton(deleteContactAction));
		getContentPane().add(southPanel, BorderLayout.SOUTH);
	}
	
	private void setupMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		JMenu editMenu = new JMenu("Edit");
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		editMenu.setMnemonic(KeyEvent.VK_E);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		fileMenu.add(new JMenuItem(newContactListAction));
		fileMenu.add(new JMenuItem(loadContactsAction));
		fileMenu.add(new JMenuItem(saveContactsAction));
		fileMenu.add(new JMenuItem(saveContactsToCurrentFileAction));
		fileMenu.add(new JMenuItem(exitAction));
		
		editMenu.add(new JMenuItem(addContactAction));
		editMenu.add(new JMenuItem(deleteContactAction));
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		setJMenuBar(menuBar);
	}

	private void setupActions() {

		loadContactsAction = new LoadContactsAction(contactListTableModel, this);
		saveContactsAction = new SaveContactsAction(contactListTableModel, this, "Save as");
		saveContactsToCurrentFileAction = new SaveContactsToCurrentFileAction(contactListTableModel, this);
		addContactAction = new AddContactAction(contactListTableModel, this);
		newContactListAction = new NewContactListAction(contactListTableModel, this);
		exitAction = new ExitAction(this);
		deleteContactAction = new DeleteContactAction(contactListTableModel, this);
	}
	
	private static final String DIRTY_MODEL_MESSAGE =
			"You have unsaved changes. Save now?";
	
	/*
	 * enum for dirtyCheck()
	 */
	public static enum DirtyCheckResult { SAVE, NO_SAVE, CANCEL, NOT_DIRTY }
	
	/*
	 * used by components attempting operations that would lead to data loss
	 * 
	 * @return result of confirm dialog
	 * 
	 */
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

	/*
	 * disable the save to current file action
	 */
	public void disableSaveAction() {
		saveContactsToCurrentFileAction.setEnabled(false);
	}
	
	/*
	 * enable the save to current file action
	 */
	public void enableSaveAction() {
		saveContactsToCurrentFileAction.setEnabled(true);
	}
	
	public void setDeleteActionEnabled(boolean b) {
		deleteContactAction.setEnabled(b);
	}
}
