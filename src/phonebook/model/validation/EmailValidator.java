package phonebook.model.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator implementation for email addresses
 */
public class EmailValidator implements Validator {

	/**
	 * regex for valid email addresses
	 */
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	/**
	 * @param email the email to validate
	 * @return true if the object is valid
	 */
	@Override
	public boolean validate(String email) {

		if (email == null || email.equals(""))
			return true;
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
		return matcher.find();

	}

}
