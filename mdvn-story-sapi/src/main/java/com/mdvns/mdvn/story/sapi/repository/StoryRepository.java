package com.mdvns.mdvn.story.sapi.repository;

import com.mdvns.mdvn.story.sapi.domain.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StoryRepository extends JpaRepository<Story, Integer>{

    Page<Story> findAllByReqmntIdAndIsDeleted(String reqmntId, Integer isDeleted, Pageable pageable);

    List<Story> findAllByReqmntIdAndIsDeletedOrderByUuIdAsc(String reqmntId, Integer isDeleted);

    Story findByStoryIdAndIsDeleted(String storyId, Integer isDelete);

    //按reqmntId查询(先按状态排序，再按时间排序，最后按优先级排序)
    @Query(value="SELECT t.* ,(CASE WHEN STATUS=\"new\" THEN 1 WHEN STATUS=\"going\" THEN 2 WHEN STATUS=\"reopen\" THEN 3 WHEN STATUS=\"finished\" THEN 4 WHEN STATUS=\"pause\" THEN 5 ELSE 6 END) state FROM story t WHERE reqmnt_Id IN ?1 ORDER BY state ,create_time DESC , priority DESC", nativeQuery = true)
    List<Story> rtrvStoryInfoList(List<String> reqmntIds);

    //按storyIds查询(先按状态排序，再按时间排序，最后按优先级排序)
    @Query(value="SELECT t.* ,(CASE WHEN STATUS=\"new\" THEN 1 WHEN STATUS=\"going\" THEN 2 WHEN STATUS=\"reopen\" THEN 3 WHEN STATUS=\"finished\" THEN 4 WHEN STATUS=\"pause\" THEN 5 ELSE 6 END) state FROM story t WHERE story_Id IN ?1 ORDER BY state ,create_time DESC , priority DESC", nativeQuery = true)
    List<Story> rtrvStoryInfoByStoryIdsList(List<String> storyIds);


    //获取story列表总条数
    @Query(value="  SELECT DISTINCT COUNT(*) FROM (SELECT * FROM story WHERE reqmntId = ?1) t ", nativeQuery = true)
    Long getStoryBaseInfoCount(String reqmntId);

    @Query(value="SELECT * from story WHERE uu_id=?1", nativeQuery = true)
    Object rtrvStoryBaseInfo(Integer uu_id);

    @Query(value="SELECT story_id FROM story WHERE uu_id = ?1", nativeQuery = true)
    String getStoryId(Integer uuId);

    Story findByStoryId(String storyId);

    @Query(value="SELECT function_label_id from requirement_info where reqmnt_id = (SELECT reqmnt_id from story WHERE story_id=?1)", nativeQuery = true)
    String getLabelIdByStoryId(String storyId);

    @Query(value="SELECT model_id from requirement_info where reqmnt_id = (SELECT reqmnt_id from story WHERE story_id=?1)", nativeQuery = true)
    String getModelIdByStoryId(String storyId);

    @Query(value="SELECT reqmnt_id from story WHERE story_id=?1", nativeQuery = true)
    String getReqmntIdByStoryId(String storyId);

    @Query(value="SELECT * from story WHERE story_id=?1", nativeQuery = true)
    Story rtrvStoryBaseInfo(String storyId);
}
