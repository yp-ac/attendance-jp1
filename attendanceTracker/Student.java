package attendanceTracker;

public class Student {
    String name;
    long enrollmentNo;
    boolean isPresent;

    Student(long enrollment_no, String name) {
        this.enrollmentNo = enrollment_no;
        this.name = name;
        this.isPresent = true;
    }

    public String getName() {
        return name;
    }

    public long getEnrollmentNo() {
        return enrollmentNo;
    }

    public void markPresent() {
        isPresent = true;
    }

    public void markAbsent() {
        isPresent = false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", enrollmentNo=" + enrollmentNo +
                ", isPresent=" + isPresent +
                '}';
    }
}
