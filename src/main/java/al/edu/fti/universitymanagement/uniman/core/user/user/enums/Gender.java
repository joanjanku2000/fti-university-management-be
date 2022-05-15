package al.edu.fti.universitymanagement.uniman.core.user.user.enums;

public enum Gender {
    MALE("Male") , FEMALE("Female") ;

    private final String text;

    Gender(final String text){
        this.text = text;
    }
    @Override
    public String toString(){
        return text;
    }
}
