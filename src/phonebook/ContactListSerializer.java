package phonebook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public class ContactListSerializer {

	/*
	 * @param path file to load from
	 * @return the ContactList object read from the file
	 */
	public ContactList load(Path path) throws IOException, ClassNotFoundException {

		ContactList contactList;

		try (
				FileInputStream fis = new FileInputStream(path.toString());
				BufferedInputStream bis = new BufferedInputStream(fis);
				ObjectInputStream ois = new ObjectInputStream(bis);) {

			contactList = (ContactList) ois.readObject();
		}

		return contactList;
	}
	
	/*
	 * @param path filename to save to
	 * @param list the contactlist to save
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(Path path, ContactList list) throws FileNotFoundException, IOException {
		
		try (
				FileOutputStream fos = new FileOutputStream(path.toString());
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				ObjectOutputStream oos = new ObjectOutputStream(bos);) {
			
			oos.writeObject(list);
		}
		
	}
}
