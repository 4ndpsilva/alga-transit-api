package com.algaworks.algatransit.domain.model.entity;

import com.algaworks.algatransit.domain.exception.AlreadyExistsException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    public void validateExistingEmail(Owner owner){
        if(existingEmail(owner)){
            throw new AlreadyExistsException(OwnerMsg.OWNER_002);
        }
    }

    private boolean existingEmail(Owner owner){
        return this.email.equals(owner.getEmail()) && !this.equals(owner);
    }

    public interface OwnerMsg{
        String OWNER_001 = "OWNER-001";
        String OWNER_002 = "OWNER-002";
    }
}