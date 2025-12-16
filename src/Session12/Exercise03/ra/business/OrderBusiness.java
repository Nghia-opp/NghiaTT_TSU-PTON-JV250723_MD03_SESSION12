package Session12.Exercise03.ra.business;

import Session12.Exercise03.ra.entity.Order;

import java.util.*;
import java.util.stream.Collectors;

public class OrderBusiness {
    private List<Order> orders = new ArrayList<>();

    // Thêm đơn hàng
    public void addOrder(Order order) {
        if (order.getCustomerName().length() < 6 || order.getCustomerName().length() > 100) {
            System.out.println("Tên khách hàng phải từ 6-100 ký tự!");
            return;
        }
        if (!order.getPhoneNumber().matches("^(0[3|5|7|8|9])[0-9]{8}$")) {
            System.out.println("Số điện thoại không hợp lệ!");
            return;
        }
        if (order.getAddress() == null || order.getAddress().isEmpty()) {
            System.out.println("Địa chỉ bắt buộc nhập!");
            return;
        }
        if (order.getOrderAmount() <= 0) {
            System.out.println("Giá trị đơn hàng phải > 0!");
            return;
        }
        orders.add(order);
        System.out.println("Thêm đơn hàng thành công!");
    }

    // Hiển thị danh sách (giảm dần theo giá trị)
    public void displayOrders() {
        orders.stream()
                .sorted(Comparator.comparing(Order::getOrderAmount).reversed())
                .forEach(System.out::println);
    }

    // Cập nhật trạng thái
    public void updateStatus(int id) {
        Optional<Order> opt = orders.stream()
                .filter(o -> o.getOrderId() == id)
                .findFirst();

        opt.ifPresentOrElse(order -> {
            switch (order.getStatus()) {
                case Pending -> order.setStatus(Order.Status.Shipped);
                case Shipped -> order.setStatus(Order.Status.Delivered);
                case Delivered -> System.out.println("Đơn hàng đã giao, không thể cập nhật!");
            }
            System.out.println("Cập nhật trạng thái thành công!");
        }, () -> System.out.println("Không tìm thấy đơn hàng!"));
    }

    // Xóa đơn hàng (chỉ khi Pending)
    public void deleteOrder(int id) {
        Optional<Order> opt = orders.stream()
                .filter(o -> o.getOrderId() == id)
                .findFirst();

        opt.ifPresentOrElse(order -> {
            if (order.getStatus() == Order.Status.Pending) {
                orders.remove(order);
                System.out.println("Đã xóa đơn hàng!");
            } else {
                System.out.println("Chỉ được xóa đơn hàng ở trạng thái Pending!");
            }
        }, () -> System.out.println("Không tìm thấy đơn hàng!"));
    }

    // Tìm kiếm theo tên khách hàng
    public void searchByCustomerName(String name) {
        List<Order> result = orders.stream()
                .filter(o -> o.getCustomerName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy đơn hàng!");
        } else {
            result.forEach(System.out::println);
        }
    }

    // Thống kê
    public void statistics() {
        System.out.println("Tổng số đơn hàng: " + orders.size());

        float totalRevenue = (float) orders.stream()
                .filter(o -> o.getStatus() == Order.Status.Delivered)
                .mapToDouble(Order::getOrderAmount)
                .sum();
        System.out.println("Tổng doanh thu từ đơn Delivered: " + totalRevenue);

        Map<Order.Status, Long> countByStatus = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
        countByStatus.forEach((status, count) ->
                System.out.println(status + ": " + count + " đơn hàng"));

        orders.stream()
                .max(Comparator.comparing(Order::getOrderAmount))
                .ifPresent(o -> System.out.println("Đơn hàng có giá trị lớn nhất: " + o));
    }

}
