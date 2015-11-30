package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;

import phonebook.viewcontroller.MainFrame;

/**
 * action called by various components to close the application properly
 */
public class ExitAction extends AbstractAction {
	
	private MainFrame frame;

	public ExitAction(MainFrame frame) {
		super("Exit");
		this.frame = frame;
		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

}
