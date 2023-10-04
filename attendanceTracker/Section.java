package attendanceTracker;

import attendanceTracker.exceptions.SectionNotFoundException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Section {
    String name;
    List<Student> students;

    Section(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    static Section get(String sectionName) throws SectionNotFoundException {
        Section section = new Section(sectionName);
        File sectionConfigFilePath = new File(AttendanceTracker.getGlobalDataFolderPath() + "/sections/" + sectionName);
        Scanner configFileScanner;

        try {
            configFileScanner = new Scanner(sectionConfigFilePath);
        } catch (FileNotFoundException e) {
            throw new SectionNotFoundException();
        }

        while (configFileScanner.hasNext()) {
            section.students.add(new Student(
                    configFileScanner.nextLong(),
                    configFileScanner.nextLine().trim()
            ));
        }

        return section;
    }
}
