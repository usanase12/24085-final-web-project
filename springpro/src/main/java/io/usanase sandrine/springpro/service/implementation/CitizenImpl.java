package io.cedricksonga.springpro.service.implementation;

import io.cedricksonga.springpro.model.Citizen;
import io.cedricksonga.springpro.repo.CitizenRepo;
import io.cedricksonga.springpro.service.CitizenInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CitizenImpl implements CitizenInterface {

    private CitizenRepo citizenRepo;
    public CitizenImpl(CitizenRepo citizenRepo){
        this.citizenRepo = citizenRepo;
    }
    @Override
    public Citizen registerCitizen(Citizen citizen) {
        return citizenRepo.save(citizen);
    }

    @Override
    public Citizen updateCitizen(Citizen citizen) {
        return citizenRepo.save(citizen);
    }

    @Override
    public void deleteCitizen(Long citizen) {
        citizenRepo.deleteById(citizen);
    }

    @Override
    public List<Citizen> citizenList() {
        return citizenRepo.findAll();
    }

    @Override
    public Citizen findCitizenByCitizenId(Long citizen) {
        return citizenRepo.findById(citizen).get();
    }

    @Override
    public Page<Citizen> pagenateCitizen(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo -1,pageSize);
        return this.citizenRepo.findAll(pageable);
    }
}
