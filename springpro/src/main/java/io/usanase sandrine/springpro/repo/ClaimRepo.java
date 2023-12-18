package io.cedricksonga.springpro.repo;

import io.cedricksonga.springpro.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepo extends JpaRepository<Claim, String> {
}
