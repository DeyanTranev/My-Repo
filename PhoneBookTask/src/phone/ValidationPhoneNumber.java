package phone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPhoneNumber {

	private static boolean firstPattern(String num) {

		String phonePattern = "\\+([3]{1}[5]{1}[9]{1})([8]{1})([7-9]{1})([2-9]{1})([0-9]{6})$";

		Pattern pattern = Pattern.compile(phonePattern);

		Matcher phoneMatcher = pattern.matcher(num);

		return phoneMatcher.matches();

	}

	private static boolean secondPattern(String num) {

		String phonePattern = "([0]{1})([8]{1})([7-9]{1})([2-9]{1})([0-9]{6})$";

		Pattern pattern = Pattern.compile(phonePattern);

		Matcher phoneMatcher = pattern.matcher(num);

		return phoneMatcher.matches();

	}

	private static boolean thirdPattern(String num) {

		String phonePattern = "([0]{2})([3]{1}[5]{1}[9]{1})([8]{1})([7-9]{1})([2-9]{1})([0-9]{6})$";

		Pattern pattern = Pattern.compile(phonePattern);

		Matcher phoneMatcher = pattern.matcher(num);

		return phoneMatcher.matches();
	}

	public static boolean getValidPhoneNumber(String num) {

		

		if (firstPattern(num)) {
			return true;
		} 
		else {
			if (secondPattern(num)) {
				return true;
			} 
			else {
				if (thirdPattern(num)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean outgoingCallValidation(String num) {
		String callPattern = "[0-9]*";

		Pattern pattern = Pattern.compile(callPattern);

		Matcher callMatcher = pattern.matcher(num);

		return callMatcher.matches();
	}
	
}
