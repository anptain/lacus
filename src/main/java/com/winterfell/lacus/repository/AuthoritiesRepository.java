package com.winterfell.lacus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.winterfell.lacus.entity.Authorities;
import com.winterfell.lacus.entity.User;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>, JpaSpecificationExecutor<Authorities> {
	List<Authorities> findByUser(User user);
}