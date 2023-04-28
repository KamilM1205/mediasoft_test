package ru.mediasoft.test.MediaSoftTest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.mediasoft.test.MediaSoftTest.models.PostOffice;
import ru.mediasoft.test.MediaSoftTest.repositories.PostOfficeRepository;

@Service
public class PostOfficeService {
	@Autowired
	private PostOfficeRepository repository;

	public void create(PostOffice postOffice) throws Exception {
		if (!repository.findByAddress(postOffice.getAddress()).isPresent()) {
			if (!postOffice.getAddress().isEmpty() && !postOffice.getName().isEmpty()) {
				repository.save(postOffice);
			} else
				throw new Exception("Fields Name and Address must be non empty in class PostOffice.");	
		} else 
			throw new Exception("PostOffice with this address already exists.");
	}

	public Optional<PostOffice> get(int id) {
		return repository.findById(id);
	}

	public List<PostOffice> getAll() {
		Iterable<PostOffice> po_iter = repository.findAll();
		List<PostOffice> list = new ArrayList<>();
		po_iter.forEach(list::add);
		return list;
	}

	public void update(PostOffice postOffice) throws Exception {
		if (repository.findById(postOffice.getId()).isPresent()) {
			if (!postOffice.getAddress().isEmpty() && !postOffice.getName().isEmpty()) {
				repository.save(postOffice);
			} else {
				throw new Exception("Fields Name and Address must be non empty in class PostOffice.");
			}
		} else {
			create(postOffice);
		}
	}

    public boolean exists(int id) {
        return repository.existsById(id);
    }

	public void delete(PostOffice postOffice) {
		repository.delete(postOffice);
	}
}
