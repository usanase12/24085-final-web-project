package io.cedricksonga.springpro.service.implementation;

import io.cedricksonga.springpro.model.Claim;
import io.cedricksonga.springpro.repo.ClaimRepo;
import io.cedricksonga.springpro.service.ClaimInterface;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Lazy
@Service
public class ClaimImpl implements ClaimInterface {

    private final ClaimRepo claimRepo;

    public ClaimImpl(ClaimRepo claimRepo) {
        this.claimRepo = claimRepo;
    }

    @Override
    public Claim saveClaim(Claim claim) {
        return claimRepo.save(claim);
    }

    @Override
    public List<Claim> GetAllClaim() {
        return claimRepo.findAll();
    }

    @Override
    public void deleteClaim(String email) {
        claimRepo.deleteById(email);
    }
}
