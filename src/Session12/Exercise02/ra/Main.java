package Session12.Exercise02.ra;

import Session12.Exercise02.ra.business.AppointmentBusiness;
import Session12.Exercise02.ra.entity.Appointment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentBusiness business = new AppointmentBusiness();

        while (true) {
            System.out.println("*********************QUẢN LÝ LỊCH HẸN********************");
            System.out.println("1. Thêm lịch hẹn");
            System.out.println("2. Hiển thị danh sách lịch hẹn");
            System.out.println("3. Tìm kiếm lịch hẹn theo tên bệnh nhân");
            System.out.println("4. Cập nhật lịch hẹn theo mã");
            System.out.println("5. Xóa lịch hẹn theo mã");
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Appointment appointment = new Appointment();
                    appointment.inputData(scanner);
                    business.addAppointment(appointment);
                    break;
                case 2:
                    business.displayAppointments();
                    break;
                case 3:
                    System.out.print("Nhập tên bệnh nhân cần tìm: ");
                    business.searchByPatientName(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Nhập mã lịch hẹn cần cập nhật: ");
                    business.updateAppointment(scanner.nextLine(), scanner);
                    break;
                case 5:
                    System.out.print("Nhập mã lịch hẹn cần xóa: ");
                    business.deleteAppointment(scanner.nextLine(), scanner);
                    break;
                case 6:
                    business.statistics();
                    break;
                case 7:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }

    }
}
