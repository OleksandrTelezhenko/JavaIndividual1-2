import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CourseDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> allCourses = FileManager.loadCourses();

        System.out.println("Реєстрація Студента");
        System.out.print("Введіть ваше ім'я: ");
        String sName = scanner.nextLine();
        System.out.print("Введіть ваш Email: ");
        String sEmail = scanner.nextLine();
        System.out.print("Введіть ваш рівень: ");
        String sLevel = scanner.nextLine();

        Student currentUser = new Student(sName, sEmail, sLevel, 0.0);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nМЕНЮ");
            System.out.println("Користувач: " + currentUser.getName() + " [" + currentUser.getEmail() + "]");
            System.out.println("1. [АДМІН] Додати новий курс (та створити вчителя)");
            System.out.println("2. Переглянути доступні курси");
            System.out.println("3. Поповнити баланс (Поточний: " + currentUser.getBalance() + " грн)");
            System.out.println("4. Записатися на курс");
            System.out.println("5. Зберегти всі дані у файл");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nСтворення профілю вчителя");
                    System.out.print("Ім'я вчителя: ");
                    String tName = scanner.nextLine();
                    System.out.print("Email вчителя: ");
                    String tEmail = scanner.nextLine();
                    System.out.print("Телефон вчителя: ");
                    String tPhone = scanner.nextLine();
                    System.out.print("Спеціалізація: ");
                    String tSpec = scanner.nextLine();

                    Teacher teacher = new Teacher(tName, tEmail, tPhone, tSpec);

                    System.out.println("\nНалаштування курсу ");
                    System.out.print("Назва курсу: ");
                    String cTitle = scanner.nextLine();

                    System.out.println("Тип проведення: 1. Онлайн (платформа)  2. Офлайн (адреса)");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    String location;
                    if (type == 1) {
                        System.out.print("Введіть назву платформи: ");
                        location = "Online: " + scanner.nextLine();
                    } else {
                        System.out.print("Введіть фізичну адресу: ");
                        location = scanner.nextLine();
                    }

                    System.out.print("Ціна курсу (грн): ");
                    double cPrice = scanner.nextDouble();
                    System.out.print("Максимальна кількість місць: ");
                    int cCap = scanner.nextInt();

                    allCourses.add(new Course(cTitle, location, cCap, cPrice, teacher));
                    System.out.println("\nКурс та викладач додані");
                    break;

                case 2:
                    if (allCourses.isEmpty()) {
                        System.out.println("\nСписок курсів порожній");
                    } else {
                        System.out.println("\nСписок доступних курсів:");
                        for (int i = 0; i < allCourses.size(); i++) {
                            System.out.println("ID [" + i + "]");
                            allCourses.get(i).getDetails();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Введіть суму для поповнення балансу: ");
                    double amount = scanner.nextDouble();
                    currentUser.topUpBalance(amount);
                    System.out.println("Баланс оновлено");
                    break;

                case 4:
                    if (allCourses.isEmpty()) {
                        System.out.println("Немає курсів для запису");
                    } else {
                        System.out.print("Введіть ID курсу, на який хочете записатись: ");
                        int id = scanner.nextInt();
                        if (id >= 0 && id < allCourses.size()) {
                            boolean success = allCourses.get(id).registerStudent(currentUser);
                            if (success) {
                                System.out.println("Ви зареєстровані на курс");
                            } else {
                                System.out.println("Недостатньо коштів на балансі або місця в групі закінчилися");
                            }
                        } else {
                            System.out.println("Курс з таким ID не знайдено");
                        }
                    }
                    break;

                case 5:
                    FileManager.saveCourses(allCourses);
                    break;

                case 0:
                    System.out.println("Завершення роботи");
                    exit = true;
                    break;

                default:
                    System.out.println("Невірний пункт меню");
            }
        }
        scanner.close();
    }
}