package attendanceTracker;
import attendanceTracker.exceptions.TeacherNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Teacher {
    String name;
    List<String> classes;
    public Teacher(String name, List<String> classes) {
        this.name = name;
        this.classes = classes;
    }
    public Teacher(int teacherId) throws TeacherNotFoundException {
        File teacherConfigFilePath = new File(AttendanceTracker.getGlobalDataFolderPath() + "/teachers/" + teacherId);
        Scanner configFileScanner;

        try {
            configFileScanner = new Scanner(teacherConfigFilePath);
        } catch (FileNotFoundException e) {
            throw new TeacherNotFoundException();
        }

        name = configFileScanner.nextLine().trim();
        classes = new ArrayList<>();

        while (configFileScanner.hasNext())
            classes.add(configFileScanner.nextLine().trim());
    }

    public String getName() {
        return name;
    }
    public List<String> getClasses() {
        return classes;
    }
}
