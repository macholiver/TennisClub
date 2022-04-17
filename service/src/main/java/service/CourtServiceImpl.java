package service;

import dao.CourtDao;
import entity.Court;
import org.springframework.stereotype.Service;


import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of Court service interface
 *
 * @author Oliver Mach
 */
@Service
public class CourtServiceImpl implements CourtService{
    @Inject
    private CourtDao repository;

    public void create(Court entity) {
        if (entity == null)
            throw new IllegalArgumentException("Given entity is null");
        repository.create(entity);
    }

    public List<Court> findAll() {
        return repository.findAll();
    }

    public Court findById(Long id) {
        return repository.findById(id);
    }
}
