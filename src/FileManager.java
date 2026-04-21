import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static final String FILE_PATH = "courses.dat";

    public static void saveCourses(List<Course> courses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(courses);
            System.out.println("Курси успішно збережено у файл");
        } catch (IOException e) {
            System.out.println("Помилка при збереженні: " + e.getMessage());
        }
    }

    public static List<Course> loadCourses() {
        List<Course> courses = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            courses = (List<Course>) ois.readObject();
            System.out.println("Курси завантажено");
        } catch (FileNotFoundException e) {
            System.out.println("Створено новий список");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка при завантаженні: " + e.getMessage());
        }
        return courses;
    }
}