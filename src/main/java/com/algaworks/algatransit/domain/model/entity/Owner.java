package com.algaworks.algatransit.domain.model.entity;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import com.algaworks.algatransit.infrastructure.exception.ErrorCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_OWNER")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    public void validateExistingEmail(Owner owner){
        if(existingEmail(owner)){
            throw new AlreadyExistsException(ErrorCode.OWNER_002.getCode());
        }
    }

    private boolean existingEmail(Owner owner){
        return this.email.equals(owner.getEmail()) && !(this.id.equals(owner.id));
    }
}