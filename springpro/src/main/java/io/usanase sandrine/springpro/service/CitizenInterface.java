package io.cedricksonga.springpro.service;

import io.cedricksonga.springpro.model.Citizen;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CitizenInterface {
    Citizen registerCitizen(Citizen citizen);
    Citizen updateCitizen(Citizen citizen);
    void deleteCitizen(Long citizen);
    List<Citizen> citizenList();
    Citizen findCitizenByCitizenId(Long citizen);
    Page<Citizen> pagenateCitizen(int pageNo, int pageSize);
}
