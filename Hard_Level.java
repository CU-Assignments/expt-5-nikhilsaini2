import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int id;
    private String designation;
    private double salary;

    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', designation='" + designation + "', salary=" + salary + "}";
    }
}

public class Hard_Level {
    private static final String FILE_NAME = "employees.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    employees.add(new Employee(name, id, designation, salary));
                    saveEmployees(employees);
                    break;
                case 2:
                    displayEmployees(employees);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
            System.out.println("Employees saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                employees = (List<Employee>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error loading employees: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found: " + e.getMessage());
            }
        }
        return employees;
    }

    private static void displayEmployees(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employee List:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }
}