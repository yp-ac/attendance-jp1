package attendanceTracker.exceptions;

public class TeacherNotFoundException extends Exception {
    public TeacherNotFoundException() {
        super("Unable to Find the provided teacher in the Database");
    }
    public TeacherNotFoundException(Throwable th) {
        super("Unable to Find the provided teacher in the Database", th);
    }
}
