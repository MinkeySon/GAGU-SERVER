package org.gagu.gagubackend.chat.repository;

import org.gagu.gagubackend.chat.domain.ChatRoom;
import org.gagu.gagubackend.chat.domain.ChatRoomMember;
import org.gagu.gagubackend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, Long> {
    boolean existsChatRoomMemberByRoomId(ChatRoom chatRoom);
    List<ChatRoomMember> findAllByRoomId(ChatRoom chatRoom);
    List<ChatRoomMember> findAllByMember(User user);
    boolean existsChatRoomMemberByRoomIdAndMember(ChatRoom id, User member);
    @Query("SELECT CASE WHEN COUNT(c1) > 0 THEN true ELSE false END FROM ChatRoomMember c1 " +
            "JOIN ChatRoomMember c2 ON c1.roomId = c2.roomId " +
            "WHERE c1.member = :userA AND c2.member = :userB")
    boolean existsChatRoomByMembers(@Param("userA") User userA, @Param("userB") User userB);
}
