package phonebook.model.validation;

/**
 * Interface used by the ContactValidator class
 */
public interface Validator {

	/**
	 * validates the given string according to the implementation
	 * 
	 * @param string the string to validate
	 * @return true if the string is valid
	 */
	boolean validate(String string);
}
