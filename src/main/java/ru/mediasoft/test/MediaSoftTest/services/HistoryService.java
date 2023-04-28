package ru.mediasoft.test.MediaSoftTest.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.mediasoft.test.MediaSoftTest.models.History;
import ru.mediasoft.test.MediaSoftTest.repositories.HistoryRepository;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository repository;

    public void create(History history) throws Exception {
        history.setArrivalDate(new Timestamp(System.currentTimeMillis()));
        repository.save(history);
    }

    public Optional<History> find(int id) {
        return repository.findById(id);
    }

    public List<History> findAllByPackageId(int id) {
        return repository.findBypackageId(id);
    }

    public Optional<History> findLastByPackageId(int id) {
        return repository.findTopBypackageIdOrderByIdDesc(id);
    }

    public void update(History history) throws Exception {
        if (!repository.existsById(history.getId())) {
            repository.save(history);
        } else {
            create(history);
        }
    }

    public boolean exists(int id) {
        return repository.existsById(id);
    }

    public void delete(History history) {
        repository.delete(history);
    }
}