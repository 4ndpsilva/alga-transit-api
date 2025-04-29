package com.algaworks.algatransit.domain.service;

import static com.algaworks.algatransit.infrastructure.exception.ErrorCode.OWNER_001;
import static com.algaworks.algatransit.infrastructure.exception.ErrorCode.OWNER_002;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import com.algaworks.algatransit.infrastructure.exception.ResourceNotFoundException;
import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.repository.OwnerRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository repository;

    @Transactional
    public Owner save(Owner entity){
        boolean existing = repository.findByEmail(entity.getEmail()).isPresent();

        if(existing){
            throw new AlreadyExistsException(OWNER_002.getCode());
        }

        return repository.save(entity);
    }

    @Transactional
    public Owner update(Long id, Owner entity){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(OWNER_001.getCode());
        }

        entity.setId(id);
        Owner existingOwner = repository.findByEmail(entity.getEmail()).orElse(Owner.builder().build());
        entity.validateExistingEmail(existingOwner);
        return repository.save(entity);
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(OWNER_001.getCode());
        }

        repository.deleteById(id);
    }

    public Owner findById(Long id){
        Optional<Owner> opOwner = repository.findById(id);

        if(opOwner.isPresent()){
            return opOwner.get();
        }

        throw new ResourceNotFoundException(OWNER_001.getCode());
    }

    public List<Owner> findByName(String name){
        return repository.findByNameContaining(name);
    }

    public List<Owner> findAll(){
        return repository.findAll();
    }
}