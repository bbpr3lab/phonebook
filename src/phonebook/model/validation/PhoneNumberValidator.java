package phonebook.model.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator implementation for phone numbers
 */
public class PhoneNumberValidator implements Validator {


	private static final Pattern VALID_PHONE_REGEX =
			Pattern.compile("^[0-9]+[0-9-]*$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean validate(String number) {
		
		if (number == null || number.equals(""))
			return true;

		Matcher matcher = VALID_PHONE_REGEX.matcher(number);
		return matcher.find();
	}

}
