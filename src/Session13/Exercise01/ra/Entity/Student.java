package Session13.ra.Entity;

import java.sql.Date;

public class Student {
    private int studentId;
    private String fullName;
    private Date dateOfBirth;
    private String email;

    // Constructor, getter, setter
    public Student() {}

    public Student(int studentId, String fullName, Date dateOfBirth, String email) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | DOB: %s | Email: %s",
                studentId, fullName, dateOfBirth, email);
    }
}
