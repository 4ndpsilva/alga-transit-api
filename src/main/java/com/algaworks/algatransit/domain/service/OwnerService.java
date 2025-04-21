package com.algaworks.algatransit.domain.service;

import static com.algaworks.algatransit.domain.model.entity.Owner.OwnerMsg.OWNER_001;
import static com.algaworks.algatransit.domain.model.entity.Owner.OwnerMsg.OWNER_002;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import com.algaworks.algatransit.domain.exception.ResourceNotFoundException;
import com.algaworks.algatransit.domain.mapper.OwnerMapper;
import com.algaworks.algatransit.domain.model.dto.OwnerDTO;
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
    private final OwnerMapper mapper;

    @Transactional
    public OwnerDTO save(OwnerDTO dto){
        Owner newOwner = mapper.toEntity(dto);
        boolean existing = repository.findByEmail(newOwner.getEmail()).isPresent();

        if(existing){
            throw new AlreadyExistsException(OWNER_002);
        }

        return mapper.toDTO(repository.save(newOwner));
    }

    @Transactional
    public OwnerDTO update(Long id, OwnerDTO dto){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(OWNER_001);
        }

        Owner owner = mapper.toEntity(dto);
        owner.setId(id);
        Owner existingOwner = repository.findByEmail(owner.getEmail()).orElse(Owner.builder().build());
        owner.validateExistingEmail(existingOwner);
        return mapper.toDTO(repository.save(owner));
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(OWNER_001);
        }

        repository.deleteById(id);
    }

    public OwnerDTO findById(Long id){
        Optional<Owner> opOwner = repository.findById(id);

        return opOwner.map(mapper::toDTO)
            .orElseThrow(() -> new ResourceNotFoundException(OWNER_001));
    }

    public List<OwnerDTO> findByName(String name){
        return mapper.toListDTO(repository.findByNameContaining(name));
    }

    public List<OwnerDTO> findAll(){
        return mapper.toListDTO(repository.findAll());
    }
}