package teamchalkling.chalkling.message_system;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teamchalkling.chalkling.jpa.BaseRepository;

import java.util.List;

@Repository
public interface MessageRepository extends BaseRepository<MessageEntity, Integer> {
    /**
     * Returns a list of founded MessageEntity entry by using its senderId as search
     * criteria.
     *
     * @param senderId  String  the id of the sender
     * @return          MessageEntity    If no user entry is found, this method returns null.
     */
    @Query("SELECT m FROM MessageEntity m WHERE m.senderId = :senderId AND m.receiverId = :receiverId AND m.messageType = :messageType")
    List<MessageEntity> findBySenderIdAndReceiverIdAndMessageType(@Param("senderId") int senderId,
                                                                  @Param("receiverId") int receiverId,
                                                                  @Param("messageType") MessageType messageType);
}
