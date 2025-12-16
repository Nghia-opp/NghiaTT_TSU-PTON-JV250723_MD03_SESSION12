package Session13.ra.business;

import Session13.ra.Entity.Student;
import Session13.ra.until.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentBusiness {
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL get_all_students()}")) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Student st = new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("email")
                );
                list.add(st);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm sinh viên
    public void addStudent(String name, Date dob, String email) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL add_student(?,?,?)}")) {
            cs.setString(1, name);
            cs.setDate(2, dob);
            cs.setString(3, email);
            cs.executeUpdate();
            System.out.println("Thêm sinh viên thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cập nhật sinh viên
    public void updateStudent(int id, String name, Date dob, String email) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL update_student(?,?,?,?)}")) {
            cs.setInt(1, id);
            cs.setString(2, name);
            cs.setDate(3, dob);
            cs.setString(4, email);
            cs.executeUpdate();
            System.out.println("Cập nhật thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm sinh viên theo ID
    public Student findStudentById(int id) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL find_student_by_id(?)}")) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Xóa sinh viên
    public void deleteStudent(int id) {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL delete_student(?)}")) {
            cs.setInt(1, id);
            cs.executeUpdate();
            System.out.println("Xóa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
