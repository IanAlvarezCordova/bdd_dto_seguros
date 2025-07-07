//File: /src/main/java/com/example/bdd_dto/repository/AutomovilRepository.java
package com.example.bdd_dto.repository;

import com.example.bdd_dto.model.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovilRepository extends JpaRepository<Automovil, Long> {
}
