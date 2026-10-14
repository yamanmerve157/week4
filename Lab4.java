
package lab4;

import java.util.ArrayList;
import java.util.Scanner;

public class Lab4 {
    
    static class Student {
        String studentID;
        String name;

        Student(String studentID, String name) {
            this.studentID = studentID;
            this.name = name;
        }

        void displayInfo() {
            System.out.println("Student ID: " + studentID + ", Name: " + name);
        }
    }

    static class Course {
        String courseName;
        int capacity;
        ArrayList<Student> enrolledStudents;

        Course(String courseName, int capacity) {
            this.courseName = courseName;
            this.capacity = capacity;
            this.enrolledStudents = new ArrayList<>();
        }

        void displayInfo() {
            System.out.println("\nCourse Name: " + courseName);
            System.out.println("Capacity: " + capacity);
            System.out.println("Enrolled Students: " + enrolledStudents.size());

            if (enrolledStudents.isEmpty()) {
                System.out.println("No students registered.");
            } else {
                for (Student student : enrolledStudents) {
                    student.displayInfo();
                }
            }
        }

        boolean registerStudent(Student student) {
            if (enrolledStudents.size() >= capacity) {
                System.out.println("This course is full. Registration failed.");
                return false;
            }

            for (Student enrolledStudent : enrolledStudents) {
                if (enrolledStudent.studentID.equals(student.studentID)) {
                    System.out.println("This student is already registered for this course.");
                    return false;
                }
            }

            enrolledStudents.add(student);
            System.out.println("Registration successful.");
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Course> courses = new ArrayList<>();

        int numberOfCourses;

        do {
            System.out.print("How many courses do you want to create? (At least 3): ");
            numberOfCourses = input.nextInt();
            input.nextLine();

            if (numberOfCourses < 3) {
                System.out.println("You must create at least 3 courses.");
            }
        } while (numberOfCourses < 3);

        for (int i = 0; i < numberOfCourses; i++) {
            System.out.println("\nCourse " + (i + 1));

            System.out.print("Enter course name: ");
            String courseName = input.nextLine();

            System.out.print("Enter classroom capacity: ");
            int capacity = input.nextInt();
            input.nextLine();

            while (capacity <= 0) {
                System.out.print("Capacity must be greater than 0. Enter again: ");
                capacity = input.nextInt();
                input.nextLine();
            }

            courses.add(new Course(courseName, capacity));
        }

        System.out.println("\nAvailable Courses:");

        for (Course course : courses) {
            System.out.println(course.courseName + " (Capacity: " + course.capacity + ")");
        }

        int choice;

        do {
            System.out.println("\n1. Register Student");
            System.out.println("2. View All Courses");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentID = input.nextLine();

                    System.out.print("Enter student name: ");
                    String studentName = input.nextLine();

                    System.out.print("Enter course name: ");
                    String requestedCourse = input.nextLine();

                    Course selectedCourse = null;

                    for (Course course : courses) {
                        if (course.courseName.equals(requestedCourse)) {
                            selectedCourse = course;
                            break;
                        }
                    }

                    if (selectedCourse == null) {
                        System.out.println("No course found with that name.");
                    } else {
                        Student student = new Student(studentID, studentName);
                        selectedCourse.registerStudent(student);
                    }
                    break;

                case 2:
                    if (courses.isEmpty()) {
                        System.out.println("No courses available.");
                    } else {
                        for (Course course : courses) {
                            course.displayInfo();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

        input.close();
    }}