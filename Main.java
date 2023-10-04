import attendanceTracker.AttendanceTracker;
import attendanceTracker.Student;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int teacherId, startHour, endHour;
        String sec;
        AttendanceTracker tracker = new AttendanceTracker();

        AttendanceTracker.setGlobalDataFolderPath("src/data");

        System.out.print("Enter Teacher ID: ");
        teacherId = sc.nextInt();
        sc.nextLine();

        if (!tracker.login(teacherId)) {
            System.out.println("Unable to Login, try again");
            System.exit(-1);
        }

        System.out.println("Welcome " + tracker.getCurrentTeacher().getName() + "!");


        String sectionOptions = "[" + String.join(", ", tracker.getCurrentTeacher().getClasses()) + "]";

        System.out.print("Select section " + sectionOptions + ": ");
        sec = sc.nextLine().trim();

        tracker.start();

        if (!tracker.setSection(sec)) {
            System.out.println("Unable to Set Section");
            System.exit(-1);
        }

        System.out.print("Enter Start hour: ");
        startHour = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter end hour: ");
        endHour = sc.nextInt();
        sc.nextLine();

        tracker.setTime(startHour, endHour);

        System.out.println("########### ----- CONFIG ----- ##########");
        tracker.printConfig();

        System.out.println("########### Attendance Marking ##########");
        for (Student s: tracker.getStudents()) {
            System.out.printf("%30s {%3d} [P/a]: ", s.getName(), s.getEnrollmentNo());

            String input = sc.nextLine().trim();
            if (input.equals("a") || input.equals("A"))
                s.markAbsent();
            else
                s.markPresent();
        }

        System.out.println("Attendance Saved to: " + tracker.saveAttendance().getAbsolutePath());

        tracker.stop();
    }
}
