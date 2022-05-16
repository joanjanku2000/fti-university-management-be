package al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums;

public class NotificationMessage {
    private static  String userJoinedCourse = "User :user joined course :course";
    private static String userSentYouAFriendRequest = "User :user sent you a friend request";
    private static String userPostedInTimeLine = "User :user posted in timeline";
    private static String userRespondedToYourFriendRequest = "User :user changed your friend request status to :status";

    public static String getUserJoinedCourseMessage(String fullname , String courseName){
        return userJoinedCourse
                .replace(":user",fullname)
                .replace(":course",courseName);
    }

    public static String getUserSentYouAFriendRequest(String fullname ){
        return userSentYouAFriendRequest
                .replace(":user",fullname);
    }

    public  static String getUserPostedInTimeLine(String fullname ){
        return userPostedInTimeLine
                .replace(":user",fullname);
    }

    public  static String getUserRespondedToYourFriendRequest(String fullname,String status){
        return userRespondedToYourFriendRequest
                .replace(":user",fullname)
                .replace(":status",status);
    }
}
