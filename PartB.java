import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int studentID;
    String name;
    double grade;

    public Student(int studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }

    public void display() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Grade: " + grade);
    }
}

public class StudentSerialization {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Riya Sharma", 9.2);

        // Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            oos.writeObject(s1);
            System.out.println("✅ Student object serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e);
        }

        // Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("\n✅ Student object deserialized successfully:");
            s2.display();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during deserialization: " + e);
        }
    }
}
