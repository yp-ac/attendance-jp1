package attendanceTracker.exceptions;

public class SectionNotFoundException extends Exception {
     public SectionNotFoundException() {
        super("Unable to Find the provided section in the Database");
     }
     public SectionNotFoundException(Throwable th) {
        super("Unable to Find the provided section in the Database", th);
     }
}
