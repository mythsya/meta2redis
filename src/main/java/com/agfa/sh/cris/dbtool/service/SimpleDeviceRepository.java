package com.agfa.sh.cris.dbtool.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.agfa.sh.cris.dbtool.domain.SimpleDevice;

public interface SimpleDeviceRepository extends Repository<SimpleDevice,String>{

	@Query(value="select d from SimpleDevice d where d.status = 'ACTIVE'")
	List<SimpleDevice> findAllActiveDevices();
	
}
