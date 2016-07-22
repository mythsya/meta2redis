package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.agfa.sh.cris.dbtool.domain.SimpleProfessional;

public interface SimpleProfessionalRepository extends Repository<SimpleProfessional,String>{

	@Query(value="select d from SimpleProfessional d where d.status = 'ACTIVE' and d.root ='HIS' and d.name is not null ")
	List<SimpleProfessional> findAllActiveProfessional();
}
