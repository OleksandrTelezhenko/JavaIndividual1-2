import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private String title;
    private String location;
    private int maxCapacity;
    private double price;
    private CourseStatus status;
    private Teacher instructor;
    private List<Student> enrolledStudents;

    public Course(String title, String location, int maxCapacity, double price, Teacher instructor) {
        this.title = title;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.price = price;
        this.instructor = instructor;
        this.status = CourseStatus.OPEN;
        this.enrolledStudents = new ArrayList<>();
    }

    public boolean registerStudent(Student s) {
        if (this.status == CourseStatus.OPEN && enrolledStudents.size() < maxCapacity && !enrolledStudents.contains(s)) {

            if (s.getBalance() >= this.price) {
                s.topUpBalance(-this.price);
                enrolledStudents.add(s);
                updateStatus();
                return true;
            } else {
                System.out.println("Недостатньо коштів");
            }

        } else if (enrolledStudents.contains(s)) {
            System.out.println("Ви вже зареєстровані на цей курс");
        } else {
            System.out.println("Реєстрація неможлива (місця закінчились або курс закрито)");
        }
        return false;
    }

    public void getDetails() {
        System.out.println("Інформація про курс");
        System.out.println("Назва: " + title + " Місце проведення: " + location);
        System.out.println("Викладач: " + instructor.getName() + " (" + instructor.getContactInfo() + ")");
        System.out.println("Ціна: " + price + " Статус: " + status);
        System.out.println("Кількість людей: " + getCurrentSize() + "/" + maxCapacity);
    }

    public void updateStatus() {
        if (enrolledStudents.size() >= maxCapacity) {
            this.status = CourseStatus.FULL;
        }
    }

    public int getCurrentSize() {
        return enrolledStudents.size();
    }
}