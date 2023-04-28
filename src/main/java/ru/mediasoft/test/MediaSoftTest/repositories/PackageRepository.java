package ru.mediasoft.test.MediaSoftTest.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.mediasoft.test.MediaSoftTest.models.Package;

public interface PackageRepository extends CrudRepository<Package, Integer> {
	Optional<Package> findById(Integer id);
	List<Package> findAll();

	Optional<Package> findByRecipientName(String name);
}
