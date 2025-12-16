package Session12.Exercise02.ra.business;

import Session12.Exercise02.ra.entity.Appointment;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AppointmentBusiness {
    private List<Appointment> appointments = new ArrayList<>();

    // Thêm lịch hẹn mới
    public void addAppointment(Appointment appointment) {
        // Validate cơ bản
        if (appointment.getPatientName().length() < 10 || appointment.getPatientName().length() > 50) {
            System.out.println("Tên bệnh nhân phải từ 10-50 ký tự!");
            return;
        }
        if (!appointment.getPhoneNumber().matches("^(0[3|5|7|8|9])[0-9]{8}$")) {
            System.out.println("Số điện thoại không hợp lệ!");
            return;
        }
        appointments.add(appointment);
        System.out.println("Thêm lịch hẹn thành công!");
    }

    // Hiển thị danh sách (sắp xếp theo ngày)
    public void displayAppointments() {
        appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDate))
                .forEach(System.out::println);
    }

    // Tìm kiếm theo tên bệnh nhân
    public void searchByPatientName(String name) {
        List<Appointment> result = appointments.stream()
                .filter(a -> a.getPatientName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy lịch hẹn!");
        } else {
            result.forEach(System.out::println);
        }
    }

    // Cập nhật lịch hẹn theo mã
    public void updateAppointment(String id, Scanner scanner) {
        Optional<Appointment> opt = appointments.stream()
                .filter(a -> a.getAppointmentId().equals(id))
                .findFirst();

        opt.ifPresentOrElse(a -> {
            System.out.print("Nhập tên bệnh nhân mới: ");
            a.setPatientName(scanner.nextLine());

            System.out.print("Nhập số điện thoại mới: ");
            a.setPhoneNumber(scanner.nextLine());

            System.out.print("Nhập ngày hẹn mới (dd/MM/yyyy): ");
            a.setAppointmentDate(LocalDate.parse(scanner.nextLine(),
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            System.out.print("Nhập bác sĩ mới: ");
            a.setDoctor(scanner.nextLine());

            System.out.println("Cập nhật thành công!");
        }, () -> System.out.println("Không tìm thấy lịch hẹn với mã: " + id));
    }

    // Xóa lịch hẹn
    public void deleteAppointment(String id, Scanner scanner) {
        Optional<Appointment> opt = appointments.stream()
                .filter(a -> a.getAppointmentId().equals(id))
                .findFirst();

        opt.ifPresentOrElse(a -> {
            System.out.print("Bạn có chắc muốn xóa? (Y/N): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("Y")) {
                appointments.remove(a);
                System.out.println("Đã xóa lịch hẹn!");
            }
        }, () -> System.out.println("Không tìm thấy lịch hẹn!"));
    }

    // Thống kê
    public void statistics() {
        System.out.println("Tổng số lịch hẹn: " + appointments.size());

        Map<String, Long> countByDoctor = appointments.stream()
                .collect(Collectors.groupingBy(Appointment::getDoctor, Collectors.counting()));

        countByDoctor.forEach((doctor, count) ->
                System.out.println("Bác sĩ " + doctor + ": " + count + " lịch hẹn"));
    }

}
