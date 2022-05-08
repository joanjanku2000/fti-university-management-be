package al.edu.fti.universitymanagement.uniman.core.user.enums;

public enum Role {
    STUDENT("Student") , ADMIN("Administrator");
    private String text;

    Role(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
