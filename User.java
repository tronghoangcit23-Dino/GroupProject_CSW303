public class User {
    protected int userID;
    protected String firstName;
    protected String lastName;
    protected String email;

    public User(int userID, String firstName, String lastName, String email) {
        if (userID <= 0) {
            throw new IllegalArgumentException("UserID must be a positive integer.");
        }
        
        validateNameAndEmail(firstName, lastName, email);
        
        this.userID = userID;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void updateProfile(String firstName, String lastName, String email) {
        validateNameAndEmail(firstName, lastName, email);
        
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
    }

    private void validateNameAndEmail(String firstName, String lastName, String email) {
        String nameRegex = "^[a-zA-Z\\s\\-']+$";
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (firstName == null || firstName.trim().isEmpty() || !firstName.trim().matches(nameRegex)) {
            throw new IllegalArgumentException("Invalid First Name. Must not be empty and only contain letters, spaces, hyphens, and apostrophes.");
        }
        if (lastName == null || lastName.trim().isEmpty() || !lastName.trim().matches(nameRegex)) {
            throw new IllegalArgumentException("Invalid Last Name. Must not be empty and only contain letters, spaces, hyphens, and apostrophes.");
        }
        if (email == null || email.trim().isEmpty() || !email.trim().matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid Email format.");
        }
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }
}