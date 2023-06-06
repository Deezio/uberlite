package uberlite.dao;

public class Validations {
	
	public static boolean validateName(String phoneNumber) {
        // Regular expression pattern for phone number validation
        String phonePattern = "^[a-zA-Z]+$";

        return phoneNumber.matches(phonePattern);
    }
	
	public static boolean validateEmail(String email) {
        // Regular expression pattern for email validation
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        return email.matches(emailPattern);
    }
	
	 public static boolean validatePhoneNumber(String phoneNumber) {
	        // Regular expression pattern for phone number validation
	        String phonePattern = "^[0-9]{10}$";

	        return phoneNumber.matches(phonePattern);
	    }
	 
	 public boolean validateLicensePlate(String licensePlate) {
		    // Define the license plate pattern
		    String pattern = "[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}[0-9]{4}";
		    
		    // Check if the license plate matches the pattern
		    return licensePlate.matches(pattern);
		}

}
