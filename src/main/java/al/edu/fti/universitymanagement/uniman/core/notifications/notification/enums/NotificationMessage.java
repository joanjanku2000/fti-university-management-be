package al.edu.fti.universitymanagement.uniman.core.notifications.notification.enums;

import java.util.HashMap;
import java.util.Map;

public class NotificationMessage {

    private static Map<NotificationType,String> notificationTypeExcludingFriendshipsStringMap = new HashMap<>();

    private static  String userJoinedCourse = "User :user joined course :course";
    private static String userSentYouAFriendRequest = "User :user sent you a friend request";
    private static String userPostedInTimeLine = "User :user posted in timeline";
    private static String userRespondedToYourFriendRequest = "User :user changed your friend request status to :status";

    static {
        notificationTypeExcludingFriendshipsStringMap.putIfAbsent(NotificationType.USER_JOINED_COURSE,userJoinedCourse);
        notificationTypeExcludingFriendshipsStringMap.putIfAbsent(NotificationType.POST_UPDATE,userPostedInTimeLine);
    }

    public static String getUserJoinedCourseMessage(String fullname , String courseName){
        return userJoinedCourse
                .replace(":user",fullname)
                .replace(":course",courseName);
    }

    public static String getUserSentYouAFriendRequest(String fullname ){
        return userSentYouAFriendRequest
                .replace(":user",fullname);
    }

    public static String getUserPostedInTimeLine(String fullname){
        return userPostedInTimeLine
                .replace(":user",fullname);
    }

    public static String getUserRespondedToYourFriendRequest(String fullname,String status){
        return userRespondedToYourFriendRequest
                .replace(":user",fullname)
                .replace(":status",status);
    }

    public static Map<NotificationType,String> getTypeMessageExcludingFriendshipMap() {
        return notificationTypeExcludingFriendshipsStringMap;
    }
}
