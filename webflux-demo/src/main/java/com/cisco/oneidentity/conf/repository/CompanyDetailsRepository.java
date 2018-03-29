package com.cisco.oneidentity.conf.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.cisco.oneidentity.conf.model.CompanyDetails;
@Repository
public interface CompanyDetailsRepository extends ReactiveMongoRepository<CompanyDetails, String> {


}
