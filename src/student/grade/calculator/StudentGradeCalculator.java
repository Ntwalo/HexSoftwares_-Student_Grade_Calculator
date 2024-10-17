/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package student.grade.calculator;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private String studentID;
    private double[] grades;

    public Student(String name, String studentID, double[] grades) {
        this.name = name;
        this.studentID = studentID;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public String getStudentID() {
        return studentID;
    }

    public double[] getGrades() {
        return grades;
    }

    public double calculateAverageGrade() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + " (" + studentID + "):");
        for (double grade : grades) {
            System.out.println(grade);
        }
    }

    public boolean hasPassed() {
        double average = calculateAverageGrade();
        return average >= 50;  // Assuming 50 is the passing grade
    }
}

public class StudentGradeCalculator {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nStudent Grade Calculator");
            System.out.println("1. Add Student");
            System.out.println("2. Display Student Grades");
            System.out.println("3. Calculate Student Average");
            System.out.println("4. Check if Student has Passed");
            System.out.println("5. Calculate Class Average");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline character

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayStudentGrades(scanner);
                    break;
                case 3:
                    calculateStudentAverage(scanner);
                    break;
                case 4:
                    checkIfStudentPassed(scanner);
                    break;
                case 5:
                    calculateClassAverage();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter number of grades: ");
        int numGrades = scanner.nextInt();
        double[] grades = new double[numGrades];
        for (int i = 0; i < numGrades; i++) {
            System.out.print("Enter grade " + (i + 1) + ": ");
            grades[i] = scanner.nextDouble();
        }
        scanner.nextLine();  // consume the newline character
        students.add(new Student(name, studentID, grades));
        System.out.println("Student added successfully.");
    }

    private static void displayStudentGrades(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                student.displayGrades();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void calculateStudentAverage(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                System.out.println("Average grade for " + student.getName() + ": " + student.calculateAverageGrade());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void checkIfStudentPassed(Scanner scanner) {
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                if (student.hasPassed()) {
                    System.out.println(student.getName() + " has passed.");
                } else {
                    System.out.println(student.getName() + " has failed.");
                }
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void calculateClassAverage() {
        if (students.isEmpty()) {
            System.out.println("No students available to calculate class average.");
            return;
        }
        double totalSum = 0;
        int totalGrades = 0;
        for (Student student : students) {
            for (double grade : student.getGrades()) {
                totalSum += grade;
                totalGrades++;
            }
        }
        double classAverage = totalSum / totalGrades;
        System.out.println("Class average grade: " + classAverage);
    }
}

