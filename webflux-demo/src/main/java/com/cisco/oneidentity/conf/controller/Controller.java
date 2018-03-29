package com.cisco.oneidentity.conf.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.cisco.oneidentity.conf.model.CompanyDetails;
import com.cisco.oneidentity.conf.repository.CompanyDetailsRepository;

@RestController
public class Controller {

    @Autowired
    private CompanyDetailsRepository repository;

    @GetMapping("/companyDetails")
    public Flux<CompanyDetails> getAllCompanyDetails() {
    	 
        return repository.findAll();
    }

    @PostMapping("/companyDetails")
    public Mono<CompanyDetails> addCompany(@Valid @RequestBody CompanyDetails companyDetails) {
        return repository.save(companyDetails);
    }

    @GetMapping("/companyDetails/{id}")
    public Mono<ResponseEntity<CompanyDetails>> getCompanyDetailsById(@PathVariable(value = "id") String companyId) {
        return repository.findById(companyId)
                .map(savedCompnyId -> ResponseEntity.ok(savedCompnyId))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/companyDetails/{id}")
    public Mono<ResponseEntity<CompanyDetails>> updateCompanyDetails(@PathVariable(value = "id") String companyId,
                                                   @Valid @RequestBody CompanyDetails compnyDetails) {
        return repository.findById(companyId)
                .flatMap(existingCompany -> {
                	existingCompany.setCompanyName(compnyDetails.getCompanyName());
                    return repository.save(existingCompany);
                })
                .map(updatedCompanyDetails -> new ResponseEntity<>(updatedCompanyDetails, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/companyDetails/{id}")
    public Mono<ResponseEntity<Void>> deleteCompany(@PathVariable(value = "id") String companyId) {

        return repository.findById(companyId)
                .flatMap(existingCompany ->
                        repository.delete(existingCompany)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // compnyDetails are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream/companyDetails", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CompanyDetails> streamAllCompanyDetails() {
        return repository.findAll();
    }
}
