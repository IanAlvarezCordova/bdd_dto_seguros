//File: /src/main/java/com/example/bdd_dto/repository/SeguroRepository.java
package com.example.bdd_dto.repository;

import com.example.bdd_dto.model.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguroRepository extends JpaRepository<Seguro, Long> {
}
