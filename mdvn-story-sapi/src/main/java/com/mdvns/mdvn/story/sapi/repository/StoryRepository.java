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
    @Query(value="SELECT t.* ,(CASE WHEN STATUS=\"new\" THEN 1 WHEN STATUS=\"going\" THEN 2 WHEN STATUS=\"reopen\" THEN 3 WHEN STATUS=\"finished\" THEN 4 WHEN STATUS=\"pause\" THEN 5 ELSE 6 END) state FROM story t WHERE reqmntId = ?1 ORDER BY state ,create_time DESC , priority DESC LIMIT ?2,?3", nativeQuery = true)
    List<Story> rtrvStoryInfoList(String reqmntId, Integer m, Integer n);
    //获取story列表总条数
    @Query(value="  SELECT DISTINCT COUNT(*) FROM (SELECT * FROM story WHERE reqmntId = ?1) t ", nativeQuery = true)
    Long getStoryBaseInfoCount(String reqmntId);

    @Query(value="SELECT * from story WHERE uu_id=?1", nativeQuery = true)
    Object rtrvStoryBaseInfo(Integer uu_id);

    @Query(value="SELECT story_id FROM story WHERE uu_id = ?1", nativeQuery = true)
    String getStoryId(Integer uuId);

    Story findByStoryId(String storyId);

    @Query(value="SELECT * from story WHERE story_id=?1", nativeQuery = true)
    Story rtrvStoryBaseInfo(String storyId);
}
