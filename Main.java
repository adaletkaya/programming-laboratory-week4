package javaapplication72;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentID;
    private String name;


    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }


    public void printStudentInfo() {
        System.out.println("ID: " + studentID + ", İsim: " + name);
    }
}


class Course {
    private String courseName;
    private int capacity;
    private List<Student> enrolledStudents;


    public Course(String courseName, int capacity) {
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }


    public void displayCourseInfo() {
        System.out.println("----------------------------------------");
        System.out.println("Ders Adı: " + courseName);
        System.out.println("Kapasite: " + capacity);
        System.out.println("Kayıtlı Öğrenci Sayısı: " + enrolledStudents.size());
        System.out.print("Kayıtlı Öğrenciler: ");
        
        if (enrolledStudents.isEmpty()) {
            System.out.println("Henüz kayıtlı öğrenci yok.");
        } else {
            List<String> names = new ArrayList<>();
            for (Student s : enrolledStudents) {
                names.add(s.getName());
            }
            System.out.println(String.join(", ", names));
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();

        
        
        int courseCount = 0;
        while (courseCount < 3) {
            System.out.print("Kaç adet ders oluşturmak istiyorsunuz? (En az 3): ");
            if (scanner.hasNextInt()) {
                courseCount = scanner.nextInt();
                scanner.nextLine(); // Satır sonu karakterini temizle
                if (courseCount < 3) {
                    System.out.println("Lütfen en az 3 ders giriniz.");
                }
            } else {
                System.out.println("Lütfen geçerli bir sayı giriniz.");
                scanner.nextLine();
            }
        }

        for (int i = 0; i < courseCount; i++) {
            System.out.println("\n--- Ders " + (i + 1) + " ---");
            System.out.print("Ders adı: ");
            String name = scanner.nextLine().trim();

            System.out.print("Sınıf kapasitesi: ");
            int cap = scanner.nextInt();
            scanner.nextLine(); // Satır sonu temizle

            courses.add(new Course(name, cap));
        }

        System.out.println("\n=== Kayıt İçin Açık Olan Dersler ===");
        for (Course c : courses) {
            System.out.println("- " + c.getCourseName() + " (Kapasite: " + c.getCapacity() + ")");
        }

        
        System.out.println("\n=== Öğrenci Kayıt Aşaması ===");
        while (true) {
            System.out.print("\nÖğrenci kaydı yapmak istiyor musunuz? (e/h): ");
            String devam = scanner.nextLine().trim().toLowerCase();
            if (devam.equals("h")) {
                break;
            }

            System.out.print("Öğrenci ID: ");
            String studentID = scanner.nextLine().trim();

            System.out.print("Öğrenci Adı: ");
            String studentName = scanner.nextLine().trim();

            System.out.print("Kayıt olunmak istenen dersin tam adı: ");
            String targetCourseName = scanner.nextLine().trim();

            
            Course targetCourse = null;
            for (Course c : courses) {
                if (c.getCourseName().equalsIgnoreCase(targetCourseName)) {
                    targetCourse = c;
                    break;
                }
            }

            if (targetCourse == null) {
                System.out.println("No course found with that name.");
                continue;
            }

         
            if (targetCourse.getEnrolledStudents().size() >= targetCourse.getCapacity()) {
                System.out.println("This course is full. Registration failed.");
                continue;
            }

   
            boolean alreadyRegistered = false;
            for (Student s : targetCourse.getEnrolledStudents()) {
                if (s.getStudentID().equalsIgnoreCase(studentID)) {
                    alreadyRegistered = true;
                    break;
                }
            }

            if (alreadyRegistered) {
                System.out.println("This student is already registered for this course.");
                continue;
            }

        
            targetCourse.getEnrolledStudents().add(new Student(studentID, studentName));
            System.out.println("Registration successful.");
        }

        System.out.println("\n========================================");
        System.out.println("          TÜM DERSLERİN ÖZETİ           ");
        System.out.println("========================================");
        for (Course c : courses) {
            c.displayCourseInfo();
        }

        scanner.close();
    }
}


