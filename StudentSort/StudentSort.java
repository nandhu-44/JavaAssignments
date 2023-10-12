import java.util.Scanner;

class Student {
    private int rollNumber;
    private String studentName;
    private int marksMath;
    private int marksScience;
    private int marksEnglish;
    private int totalMarks;

    public Student(int rollNumber, String studentName, int marksMath, int marksScience, int marksEnglish) {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marksMath = marksMath;
        this.marksScience = marksScience;
        this.marksEnglish = marksEnglish;
        this.totalMarks = marksMath + marksScience + marksEnglish;
    }

    public void print() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);
        System.out.println("Math Marks: " + marksMath);
        System.out.println("Science Marks: " + marksScience);
        System.out.println("English Marks: " + marksEnglish);
        System.out.println("Total Marks: " + totalMarks);
        System.out.println();
    }

    public int getTotalMarks() {
        return totalMarks;
    }
}

class StudentList {
    int count;
    Student[] students;

    public StudentList(int count) {
        this.count = count;
        students = new Student[count];
    }

    public void print() {
        System.out.println("-----Sorted List-----\n");
        for (int i = 0; i < count; i++) {
            students[i].print();
        }
    }

    public void read() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("\n-----Student " + (i + 1) + "-----");
            int rollNumber = i + 1;
            System.out.print("Student Name: ");
            String studentName = scanner.next();
            System.out.print("Math Marks: ");
            int marksMath = scanner.nextInt();
            System.out.print("Science Marks: ");
            int marksScience = scanner.nextInt();
            System.out.print("English Marks: ");
            int marksEnglish = scanner.nextInt();
            students[i] = new Student(rollNumber, studentName, marksMath, marksScience, marksEnglish);
            scanner.nextLine();
        }
        scanner.close();
    }

    public void sort() {
        for (int i = 1; i < count; i++) {
            int j = i - 1;
            while (j >= 0 && students[j].getTotalMarks() < students[j + 1].getTotalMarks()) {
                Student temp = students[j];
                students[j] = students[j + 1];
                students[j + 1] = temp;
                j--;
            }
        }
    }
}

public class StudentSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();
        StudentList studentList = new StudentList(count);
        studentList.read();
        studentList.sort();
        studentList.print();
        scanner.close();
    }
}
