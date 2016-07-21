package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.agfa.sh.cris.dbtool.domain.SimpleDepartment;

public interface SimpleDepartmentRepository extends Repository<SimpleDepartment, String>{

	@Query(value="select d from SimpleDepartment d where d.status = 'ACTIVE' and d.type is not null ")
	List<SimpleDepartment> findAllActiveDepartments();
	
}
