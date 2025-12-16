package Session12.Exercise03.ra;

import Session12.Exercise03.ra.business.OrderBusiness;
import Session12.Exercise03.ra.entity.Order;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderBusiness business = new OrderBusiness();

        while (true) {
            System.out.println("*********************QUẢN LÝ ĐƠN HÀNG********************");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Hiển thị danh sách đơn hàng");
            System.out.println("3. Cập nhật trạng thái đơn hàng theo mã");
            System.out.println("4. Xóa đơn hàng theo mã");
            System.out.println("5. Tìm kiếm đơn hàng theo tên khách hàng");
            System.out.println("6. Thống kê tổng số đơn hàng");
            System.out.println("7. Thống kê tổng doanh thu Delivered");
            System.out.println("8. Thống kê số lượng đơn hàng theo trạng thái");
            System.out.println("9. Tìm đơn hàng có giá trị lớn nhất");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Order order = new Order();
                    order.inputData(scanner);
                    business.addOrder(order);
                    break;
                case 2:
                    business.displayOrders();
                    break;
                case 3:
                    System.out.print("Nhập mã đơn hàng cần cập nhật: ");
                    business.updateStatus(Integer.parseInt(scanner.nextLine()));
                    break;
                case 4:
                    System.out.print("Nhập mã đơn hàng cần xóa: ");
                    business.deleteOrder(Integer.parseInt(scanner.nextLine()));
                    break;
                case 5:
                    System.out.print("Nhập tên khách hàng cần tìm: ");
                    business.searchByCustomerName(scanner.nextLine());
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                    business.statistics();
                    break;
                case 10:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
