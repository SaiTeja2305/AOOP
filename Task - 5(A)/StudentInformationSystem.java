import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Interfaces

interface Manageable {
    String getId();
    String getName();
}

interface Enrollable {
    void enroll();
    void unenroll();
}

// Classes

// Student class implementing Manageable and Enrollable
class Student implements Manageable, Enrollable {
    private String id;
    private String name;
    private List<String> enrolledCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public void enroll() {
        // Enrollment logic can be implemented here
    }

    @Override
    public void unenroll() {
        // Unenrollment logic can be implemented here
    }
}

// Course class implementing Manageable
class Course implements Manageable {
    private String id;
    private String name;
    private List<Student> enrolledStudents;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
        this.enrolledStudents = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
        System.out.println("Student " + student.getName() + " enrolled in course " + name);
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
        System.out.println("Student " + student.getName() + " unenrolled from course " + name);
    }
}

// EnrollmentManager class for handling enrollments
class EnrollmentManager {
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();

    public void addStudent(Student student) {
        students.put(student.getId(), student);
        System.out.println("Student added: " + student.getName());
    }

    public void addCourse(Course course) {
        courses.put(course.getId(), course);
        System.out.println("Course added: " + course.getName());
    }

    public void enrollStudentInCourse(String studentId, String courseId) {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);
        if (student != null && course != null) {
            course.addStudent(student);
            student.getEnrolledCourses().add(courseId);
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    public void unenrollStudentFromCourse(String studentId, String courseId) {
        Student student = students.get(studentId);
        Course course = courses.get(courseId);
        if (student != null && course != null) {
            course.removeStudent(student);
            student.getEnrolledCourses().remove(courseId);
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    public void listStudents() {
        System.out.println("Students:");
        for (Student student : students.values()) {
            System.out.println("ID: " + student.getId() + ", Name: " + student.getName() + ", Enrolled Courses: " + student.getEnrolledCourses());
        }
    }

    public void listCourses() {
        System.out.println("Courses:");
        for (Course course : courses.values()) {
            System.out.println("ID: " + course.getId() + ", Name: " + course.getName() + ", Enrolled Students: " + course.getEnrolledStudents().size());
        }
    }
}

// Main Class
public class StudentInformationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnrollmentManager manager = new EnrollmentManager();

        while (true) {
            System.out.println("\nStudent Information System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Unenroll Student from Course");
            System.out.println("5. List Students");
            System.out.println("6. List Courses");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Student
                    System.out.println("Enter student ID:");
                    String studentId = scanner.nextLine();
                    System.out.println("Enter student name:");
                    String studentName = scanner.nextLine();
                    manager.addStudent(new Student(studentId, studentName));
                    break;

                case 2: // Add Course
                    System.out.println("Enter course ID:");
                    String courseId = scanner.nextLine();
                    System.out.println("Enter course name:");
                    String courseName = scanner.nextLine();
                    manager.addCourse(new Course(courseId, courseName));
                    break;

                case 3: // Enroll Student in Course
                    System.out.println("Enter student ID to enroll:");
                    String enrollStudentId = scanner.nextLine();
                    System.out.println("Enter course ID to enroll in:");
                    String enrollCourseId = scanner.nextLine();
                    manager.enrollStudentInCourse(enrollStudentId, enrollCourseId);
                    break;

                case 4: // Unenroll Student from Course
                    System.out.println("Enter student ID to unenroll:");
                    String unenrollStudentId = scanner.nextLine();
                    System.out.println("Enter course ID to unenroll from:");
                    String unenrollCourseId = scanner.nextLine();
                    manager.unenrollStudentFromCourse(unenrollStudentId, unenrollCourseId);
                    break;

                case 5: // List Students
                    manager.listStudents();
                    break;

                case 6: // List Courses
                    manager.listCourses();
                    break;

                case 7: // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
