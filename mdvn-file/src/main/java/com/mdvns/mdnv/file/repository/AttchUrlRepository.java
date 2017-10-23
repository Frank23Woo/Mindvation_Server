package com.mdvns.mdnv.file.repository;

import com.mdvns.mdnv.file.domain.entity.AttachUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AttchUrlRepository extends JpaRepository<AttachUrl, Integer> {

    List<AttachUrl> findByBelongTo(String belongTo);
    AttachUrl findByBelongToAndUrl(String belongTo,String url);

    @Query(value="SELECT * FROM attch_url WHERE belong_to =?1 AND is_deleted = 0;",nativeQuery = true )
    List<AttachUrl> findAttchUrls(String belongTo);
}
