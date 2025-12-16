package Session13.ra;

import Session13.ra.Entity.Student;
import Session13.ra.business.StudentBusiness;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentBusiness business = new StudentBusiness();

        while (true) {
            System.out.println("*************** QUẢN LÝ SINH VIÊN ***************");
            System.out.println("1. Hiển thị tất cả sinh viên");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Tìm sinh viên theo ID");
            System.out.println("5. Xóa sinh viên");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    List<Student> list = business.getAllStudents();
                    list.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Ngày sinh (yyyy-mm-dd): ");
                    Date dob = Date.valueOf(scanner.nextLine());
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    business.addStudent(name, dob, email);
                    break;
                case 3:
                    System.out.print("ID cần cập nhật: ");
                    int idUpdate = Integer.parseInt(scanner.nextLine());
                    System.out.print("Tên mới: ");
                    String newName = scanner.nextLine();
                    System.out.print("Ngày sinh mới (yyyy-mm-dd): ");
                    Date newDob = Date.valueOf(scanner.nextLine());
                    System.out.print("Email mới: ");
                    String newEmail = scanner.nextLine();
                    business.updateStudent(idUpdate, newName, newDob, newEmail);
                    break;
                case 4:
                    System.out.print("Nhập ID: ");
                    int idFind = Integer.parseInt(scanner.nextLine());
                    Student st = business.findStudentById(idFind);
                    System.out.println(st != null ? st : "Không tìm thấy!");
                    break;
                case 5:
                    System.out.print("Nhập ID cần xóa: ");
                    int idDelete = Integer.parseInt(scanner.nextLine());
                    business.deleteStudent(idDelete);
                    break;
                case 6:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }

    }
}
