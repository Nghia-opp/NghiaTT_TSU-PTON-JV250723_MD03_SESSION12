package Session12.Exercise03.ra.entity;

import java.util.Scanner;

public class Order {
    private static int autoId = 1; // tự tăng
    private int orderId;
    private String customerName;   // 6-100 ký tự
    private String phoneNumber;    // số điện thoại VN
    private String address;        // bắt buộc nhập
    private float orderAmount;     // >0
    private Status status;         // Pending, Shipped, Delivered

    public enum Status {
        Pending, Shipped, Delivered
    }

    // Constructor không tham số
    public Order() {
        this.orderId = autoId++;
        this.status = Status.Pending;
    }

    // Constructor đầy đủ
    public Order(String customerName, String phoneNumber, String address, float orderAmount) {
        this.orderId = autoId++;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderAmount = orderAmount;
        this.status = Status.Pending;
    }

    // Getter/Setter
    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public float getOrderAmount() { return orderAmount; }
    public void setOrderAmount(float orderAmount) { this.orderAmount = orderAmount; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {
        System.out.print("Nhập tên khách hàng (6-100 ký tự): ");
        this.customerName = scanner.nextLine();

        System.out.print("Nhập số điện thoại (VN): ");
        this.phoneNumber = scanner.nextLine();

        System.out.print("Nhập địa chỉ giao hàng: ");
        this.address = scanner.nextLine();

        System.out.print("Nhập giá trị đơn hàng (>0): ");
        try {
            this.orderAmount = Float.parseFloat(scanner.nextLine());
            if (this.orderAmount <= 0) {
                throw new IllegalArgumentException("Giá trị đơn hàng phải > 0");
            }
        } catch (NumberFormatException e) {
            System.out.println("Giá trị nhập không hợp lệ!");
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Khách hàng: %s | SĐT: %s | Địa chỉ: %s | Giá trị: %.2f | Trạng thái: %s",
                orderId, customerName, phoneNumber, address, orderAmount, status);
    }

}
