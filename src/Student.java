public class Student extends User {
    private String skillLevel;
    private double balance;

    public Student(String name, String email, String skillLevel, double balance) {
        super(name, email);
        this.skillLevel = skillLevel;
        this.balance = balance;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void topUpBalance(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}