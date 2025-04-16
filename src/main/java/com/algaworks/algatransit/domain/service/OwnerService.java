package com.algaworks.algatransit.domain.service;

import com.algaworks.algatransit.domain.mapper.OwnerMapper;
import com.algaworks.algatransit.domain.model.dto.OwnerDTO;
import com.algaworks.algatransit.domain.model.entity.Owner;
import com.algaworks.algatransit.domain.repository.OwnerConcreteRepository;
import com.algaworks.algatransit.domain.repository.OwnerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository repository;
    private final OwnerConcreteRepository concreteRepository;
    private final OwnerMapper mapper;

    @Transactional
    public OwnerDTO save(OwnerDTO dto){
        Owner owner = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(owner));
    }

    @Transactional
    public OwnerDTO update(Long id, OwnerDTO dto){
        if(!repository.existsById(id)){
            throw new RuntimeException("Owner not found");
        }

        Owner owner = mapper.toEntity(dto);
        owner.setId(id);
        return mapper.toDTO(repository.save(owner));
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Owner not found");
        }

        repository.deleteById(id);
    }

    public OwnerDTO findById(Long id){
        if(!repository.existsById(id)){
            throw new RuntimeException("Owner not found");
        }

        return mapper.toDTO(repository.findById(id).get());
    }

    public List<OwnerDTO> findByName(String name){
        return mapper.toListDTO(repository.findByNameContaining(name));
    }

    public List<OwnerDTO> findAll(){
        return mapper.toListDTO(repository.findAll());
    }

    public List<OwnerDTO> findAllByConcreteRepository(){
        return mapper.toListDTO(concreteRepository.findAll());
    }
}