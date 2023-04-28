package ru.mediasoft.test.MediaSoftTest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.mediasoft.test.MediaSoftTest.models.Package;
import ru.mediasoft.test.MediaSoftTest.repositories.PackageRepository;

@Service
public class PackageService {
	@Autowired
	private PackageRepository repository;

	public Optional<Package> create(Package pckg) throws Exception {
		if (!pckg.getRecipientAddress().isEmpty() && !pckg.getRecipientName().isEmpty()) {
			return Optional.of(repository.save(pckg));
		} else
			throw new Exception("All fields must be non empty in class Package.");
	}

	public Optional<Package> get(int id) {
		return repository.findById(id);
	}

	public List<Package> getAll() {
		return repository.findAll();
	}

	public void update(Package pckg) throws Exception {
		if (repository.existsById(pckg.getId())) {
			if (!pckg.getRecipientAddress().isEmpty() && !pckg.getRecipientName().isEmpty()) {
				repository.save(pckg);
			} else {
				throw new Exception("All fields must be non empty in class Package.");
			}
		} else {
			create(pckg);
		}
	}

	public boolean exists(int id) {
		return repository.existsById(id);
	}

	public void delete(Package pckg) {
		repository.delete(pckg);
	}
}