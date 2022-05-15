package al.edu.fti.universitymanagement.base.core.validator.exceptions.messages;

public class ErrorMessages {
    public static String NOT_FOUND = "Entity with given ID wasn't found ";
    public static String DATES_OUT_OF_BOUNDS = "Dates given in the course aren't correct";
    public static String NOT_ALLOWED = "Delete operation not allowed on this entity ";
    public static String CANNOT_UPDATE_USER = "Cannot update or delete user";
    public static String CANNOT_CHANGE_EMAIL = "Email is not updatable";
    public static String STUDENT_IS_ALREADY_PART_OF_THIS_COURSE = "Student is already registered";
    public static String LIKE_EXISTS = "Like already exists";
    public static String GENERIC_NOT_ALLOWED = "Action not allowed";
}
