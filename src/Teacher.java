public class Teacher extends User {
    private String phoneNumber;
    private String specialization;

    public Teacher(String name, String email, String phoneNumber, String specialization) {
        super(name, email);
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }

    public String getContactInfo() {
        return specialization + (",") + " Phone: " + phoneNumber + (",") + " Email: " + email;
    }
}