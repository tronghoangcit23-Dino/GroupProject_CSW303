//User Class done
public class User {
    protected int userID;
    protected String firstName;
    protected String lastName;
    protected String email;

    public User(int userID, String firstName, String lastName, String email) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void updateProfile(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }
}