package phonebook.model.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {


	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean validate(String email) {

		if (email == null)
			return true;
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
		return matcher.find();

	}

}
