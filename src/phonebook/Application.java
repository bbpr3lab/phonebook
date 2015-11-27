package phonebook;

import phonebook.viewcontroller.MainFrame;

public class Application {
	
	private MainFrame mainFrame;

	Application() {
		this.mainFrame = new MainFrame();
	}
	
	void run() {
		mainFrame.setVisible(true);
	}

	/*
	 * Application entry point
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		new Application().run();
	}

}
