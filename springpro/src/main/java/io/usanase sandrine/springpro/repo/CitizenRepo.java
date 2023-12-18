package io.cedricksonga.springpro.repo;

import io.cedricksonga.springpro.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen, Long> {
}
