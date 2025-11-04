import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    String designation;
    double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.printf("ID: %d | Name: %s | Designation: %s | Salary: %.2f\n", id, name, designation, salary);
    }
}

public class EmployeeManagementSystem {
    static final String FILE_NAME = "employees.dat";

    public static void addEmployee() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(id, name, designation, salary);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME, true)) {
            @Override
            protected void writeStreamHeader() throws IOException {
                reset(); // prevent stream corruption when appending
            }
        }) {
            oos.writeObject(emp);
            System.out.println("✅ Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving employee: " + e);
        }
    }

    public static void displayAllEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("\n--- Employee Records ---");
            while (true) {
                Employee emp = (Employee) ois.readObject();
                emp.display();
            }
        } catch (EOFException e) {
            System.out.println("\n✅ End of file reached.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employees: " + e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== Employee Management Menu =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> displayAllEmployees();
                case 3 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }
}
