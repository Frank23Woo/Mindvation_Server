package com.mdvns.mdvn.model.sapi.repository;

import com.mdvns.mdvn.model.sapi.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
