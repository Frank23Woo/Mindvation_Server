package com.mdvns.mdvn.staff.sapi.repository;

import com.mdvns.mdvn.staff.sapi.domain.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Page<Staff> findAll(Pageable pageable);

    Staff findByStaffId(String staffId);

    Page<Staff> findByNameStartingWith(String pswd, Pageable pageable);

    @Query(value="SELECT DISTINCT COUNT(*) FROM staff ", nativeQuery = true)
    Long getStaffCount();

    Page<Staff> findByNameLike(String name ,Pageable pageable);


    Staff findByAccountAndPassword(String account, String password);

    Staff findFirstByName(String name);
    Staff findFirstByAccount(String account);

    List<Staff> findAllByAccountIsNot(String account);

    Page<Staff> findAllByAccountIsNot(String account,Pageable pageable);

//    @Query(value = "select * from staff where name like %?1", nativeQuery = true)
    List<Staff> findByNameLike(String name);


    List<Staff> findByNameStartingWith(String startingStr);


}
