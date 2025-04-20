package com.algaworks.algatransit.domain.model.entity;

import com.algaworks.algatransit.domain.exception.BusinessException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
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

    private String name;
    private String email;
    private String phone;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Owner owner) && this.id.equals(owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void validateExistingEmail(Owner owner){
        if(existingEmail(owner)){
            throw new BusinessException("O email informado já existe");
        }
    }

    private boolean existingEmail(Owner owner){
        return this.email.equals(owner.getEmail()) && !this.equals(owner);
    }
}