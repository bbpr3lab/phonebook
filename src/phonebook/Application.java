package phonebook;

import phonebook.viewcontroller.MainFrame;

public class Application {
	
	/**
	 * the main window of the application
	 */
	private MainFrame mainFrame;

	/**
	 * constructor
	 */
	Application() {
		this.mainFrame = new MainFrame();
	}
	
	/**
	 * show the main window
	 */
	void run() {
		mainFrame.setVisible(true);
	}

	/**
	 * Application entry point
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		new Application().run();
	}

}
