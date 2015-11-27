package phonebook.viewcontroller.actions;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;

import phonebook.viewcontroller.MainFrame;

public class ExitAction extends AbstractAction {
	
	private MainFrame frame;

	public ExitAction(MainFrame frame) {
		super("Exit");
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

}
