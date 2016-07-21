package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.agfa.sh.cris.dbtool.domain.SimpleExamItem;

public interface SimpleExamItemRepository extends Repository<SimpleExamItem, String>{

	@Query(value="select u from SimpleExamItem u where u.enabled = true and u.domain=?1 ")
	List<SimpleExamItem> findAllEnabled(String domain);
}
