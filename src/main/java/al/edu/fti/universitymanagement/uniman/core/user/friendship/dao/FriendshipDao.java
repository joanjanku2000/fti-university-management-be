package al.edu.fti.universitymanagement.uniman.core.user.friendship.dao;

import al.edu.fti.universitymanagement.base.core.dao.BaseDao;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.entity.FriendshipEntity;
import al.edu.fti.universitymanagement.uniman.core.user.friendship.enums.FriendshipStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendshipDao extends BaseDao<FriendshipEntity, Long> {

    /**
     * This method finds friends of given user.
     * The user might be the sender or the receiver of the friendship request
     * given that the request status is FRIENDS
     * @param senderId  Sender Id
     * @param receiverId Receiver Id
     * @return Page containing the found matches
     */
    @Query("select f from FriendshipEntity f where " +
            "(f.sender.id = :senderId or f.receiver.id = :receiverId) and f.status = :status ")
    Page<FriendshipEntity> findAllBySenderIdOrReceiverIdAndStatus(Long senderId, Long receiverId, FriendshipStatus status, Pageable pageable);

    /**
     * This method finds the request towards the user id given in the parameter
     * meaning the friendship status must be PENDING
     * @param receiverId User ID
     * @return A List containing the friendship requests
     */
    List<FriendshipEntity> findAllByReceiverIdAndStatus(Long receiverId, FriendshipStatus status);
}
