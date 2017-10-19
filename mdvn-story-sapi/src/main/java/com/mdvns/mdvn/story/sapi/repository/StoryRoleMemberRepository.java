package com.mdvns.mdvn.story.sapi.repository;

import com.mdvns.mdvn.story.sapi.domain.entity.StoryRoleMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRoleMemberRepository extends JpaRepository<StoryRoleMember,Integer>{

    StoryRoleMember findByStoryIdAndRoleIdAndStaffId(String storyId,String roleId,String staffId);

    @Query(value="SELECT * FROM staff_role_story_map WHERE story_id =?1 AND role_id =?2 AND is_deleted = 0;",nativeQuery = true )
    List<StoryRoleMember> rtrvMembersByRoleId(String storyId,String roleId);

    @Query(value="SELECT * FROM staff_role_story_map WHERE story_id =?1 AND is_deleted = 0;",nativeQuery = true )
    List<StoryRoleMember> findSRoleMembers(String storyId);

}
