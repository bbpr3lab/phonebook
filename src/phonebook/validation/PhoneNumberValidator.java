package phonebook.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements Validator {


	private static final Pattern VALID_PHONE_REGEX =
			Pattern.compile("^[0-9]+[0-9-]*$", Pattern.CASE_INSENSITIVE);

	@Override
	public boolean validate(String number) {
		
		if (number == null)
			return true;

		Matcher matcher = VALID_PHONE_REGEX.matcher(number);
		return matcher.find();
	}

}
