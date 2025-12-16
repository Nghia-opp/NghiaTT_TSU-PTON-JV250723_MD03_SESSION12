package Session12.Exercise02.ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Appointment {
    private String appointmentId;   // 6 ký tự không trùng lặp
    private String patientName;     // 10-50 ký tự
    private String phoneNumber;     // số điện thoại VN
    private LocalDate appointmentDate; // dd/MM/yyyy
    private String doctor;          // tối đa 200 ký tự

    // Constructor không tham số
    public Appointment() {}

    // Constructor đầy đủ tham số
    public Appointment(String appointmentId, String patientName, String phoneNumber,
                       LocalDate appointmentDate, String doctor) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
    }

    // Getter/Setter
    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Nhập mã lịch hẹn (6 ký tự): ");
        this.appointmentId = scanner.nextLine();

        System.out.print("Nhập tên bệnh nhân (10-50 ký tự): ");
        this.patientName = scanner.nextLine();

        System.out.print("Nhập số điện thoại (VN): ");
        this.phoneNumber = scanner.nextLine();

        System.out.print("Nhập ngày hẹn (dd/MM/yyyy): ");
        this.appointmentDate = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Nhập tên bác sĩ (<=200 ký tự): ");
        this.doctor = scanner.nextLine();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("ID: %s | Bệnh nhân: %s | SĐT: %s | Ngày hẹn: %s | Bác sĩ: %s",
                appointmentId, patientName, phoneNumber,
                appointmentDate.format(formatter), doctor);
    }

}
