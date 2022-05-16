package al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums;

public class NotificationMessage {
    private String userJoinedCourse = "User :user joined course :course";
    private String userSentYouAFriendRequest = "User :user sent you a friend request";
    private String userPostedInTimeLine = "User :user posted in timeline";

    public String getUserJoinedCourseMessage(String fullname , String courseName){
        String resulting = userJoinedCourse
                .replace(":user",fullname)
                .replace(":course",courseName);
        return resulting;
    }
    public String getUserSentYouAFriendRequest(String fullname , String courseName){
        String resulting = userSentYouAFriendRequest
                .replace(":user",fullname) ;
        return resulting;
    }
    public String getUserPostedInTimeLine(String fullname , String courseName){
        String resulting = userPostedInTimeLine
                .replace(":user",fullname) ;
        return resulting;
    }
}
