package attendanceTracker;
import attendanceTracker.exceptions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

public class AttendanceTracker {
    static String globalDataFolderPath = "./tracker-data";
    Teacher currentTeacher;
    Section currentSection;
    LocalDate date;
    LocalTime startTime, endTime;
    boolean attendanceInProgress = false;

    public boolean login(int teacherId) {
        try {
            currentTeacher = new Teacher(teacherId);
            return true;
        } catch (TeacherNotFoundException e) {
            return false;
        }
    }

    public boolean setSection(String section) {
        if (!getCurrentTeacher().getClasses().contains(section)) return false;

        try {
            currentSection = Section.get(section);
            return true;
        } catch (SectionNotFoundException e) {
            return false;
        }
    }

    public void printConfig() {
        System.out.println("Teacher: " + getCurrentTeacher().getName());
        System.out.println("Section: " + currentSection.name);
        System.out.println("Date   : " + date);
        System.out.println("Start  : " + startTime);
        System.out.println("End    : " + endTime);
    }

    public File saveAttendance() {
        File outputFile;
        FileWriter fw;

        try {
            StringBuilder outputPath = new StringBuilder();
            outputPath.append(getGlobalDataFolderPath())
                    .append("/output/")
                    .append(currentSection.name)
                    .append("_")
                    .append(date.toString())
                    .append("_")
                    .append(startTime.getHour())
                    .append("-")
                    .append(endTime.getHour())
                    .append(".csv");

            outputFile = new File(String.valueOf(outputPath));
            fw = new FileWriter(outputFile);

            for (Student s: getStudents()){
                fw.write(s.getEnrollmentNo() + "," + (s.isPresent ? 1 : 0) + '\n');
            }

            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return outputFile;
    }

    public void start() {
        attendanceInProgress = true;
        date = LocalDate.now();
    }

    public void stop() {
        date = null;
        startTime = null;
        endTime = null;
        attendanceInProgress = false;
    }

    public Teacher getCurrentTeacher() {
        return currentTeacher;
    }

    public final List<Student> getStudents() {
        return Collections.unmodifiableList(currentSection.students);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime start, LocalTime end) {
        startTime = start;
        endTime = end;
    }

    public void setTime(int startHour, int endHour) {
        startTime = LocalTime.of(startHour, 0);
        endTime = LocalTime.of(endHour, 0);
    }

    public static void setGlobalDataFolderPath(String globalDataFolderPath) {
        AttendanceTracker.globalDataFolderPath = globalDataFolderPath;
    }

    public static String getGlobalDataFolderPath() {
        return globalDataFolderPath;
    }

    public boolean isInProgress() {
        return attendanceInProgress;
    }
}
