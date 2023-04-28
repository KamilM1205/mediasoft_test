package ru.mediasoft.test.MediaSoftTest.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ru.mediasoft.test.MediaSoftTest.models.PostOffice;

public interface PostOfficeRepository extends CrudRepository<PostOffice, Integer> {
	Optional<PostOffice> findById(Integer id);

	Optional<PostOffice> findByAddress(String address);
}
