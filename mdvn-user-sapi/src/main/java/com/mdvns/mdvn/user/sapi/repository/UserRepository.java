package com.mdvns.mdvn.user.sapi.repository;

import com.mdvns.mdvn.user.sapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount(String accouont);
}
