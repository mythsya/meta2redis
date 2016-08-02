package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.agfa.sh.cris.dbtool.domain.SimpleUser;

public interface SimpleUserRepository extends CrudRepository<SimpleUser, String>{

	@Query(value="select u from SimpleUser u where u.enabled = true and u.currentGroup is not null ")
	List<SimpleUser> findAllEnabledUsers();
	
	@Modifying
	@Query(value="update SimpleUser u set u.password=?2 where u.id=?1 ")
	void changePassword(String userId, String newPwd);
}
