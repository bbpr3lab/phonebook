package phonebook.validation;

import phonebook.Contact;

public class ContactValidator {
	
	private Validator phoneValidator = new PhoneNumberValidator();
	private Validator emailValidator = new EmailValidator();

	/*
	 * validates contact
	 * null values are OK
	 * @param contact the contact to validate
	 * @return returns true, if all the validators return true
	 */
	public boolean validate(Contact contact) {
		
		String firstname = contact.getFirstname();
		
		String workEmail = contact.getWorkEmail();
		String personalEmail = contact.getPersonalEmail();
		
		String homeNumber = contact.getHomeNumber();
		String workNumber = contact.getWorkNumber();
		String cellNumber = contact.getCellNumber();
		
		boolean workEmailOK = emailValidator.validate(workEmail);
		boolean personalEmailOK = emailValidator.validate(personalEmail);
		
		boolean homeNumberOK = phoneValidator.validate(homeNumber);
		boolean workNumberOK = phoneValidator.validate(workNumber);
		boolean cellNumberOK = phoneValidator.validate(cellNumber);

		return firstname != null
				&& workEmailOK && personalEmailOK
				&& homeNumberOK && workNumberOK && cellNumberOK;
	}

}
