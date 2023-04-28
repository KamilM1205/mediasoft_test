package ru.mediasoft.test.MediaSoftTest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.mediasoft.test.MediaSoftTest.models.History;


public interface HistoryRepository extends CrudRepository<History, Integer> {
	Optional<History> findById(Integer id);

	List<History> findBypackageId(Integer id);

	Optional<History> findTopBypackageIdOrderByIdDesc(int packageId);
}
