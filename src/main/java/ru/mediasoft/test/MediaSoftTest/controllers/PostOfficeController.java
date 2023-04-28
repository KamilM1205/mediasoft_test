package ru.mediasoft.test.MediaSoftTest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.mediasoft.test.MediaSoftTest.models.PostOffice;
import ru.mediasoft.test.MediaSoftTest.services.PostOfficeService;

@RestController
@RequestMapping(value = "/postoffice/*")
public class PostOfficeController {
    private final PostOfficeService service;

    @Autowired
    public PostOfficeController(PostOfficeService service) {
        this.service = service;
    }

    @GetMapping(value = "/get_info")
    @ResponseBody
    public ResponseEntity<PostOffice> get_info(@RequestParam("id") int id) {
        if (service.exists(id)) {
            return new ResponseEntity<PostOffice>(service.get(id).get(), HttpStatus.FOUND);
        }

        return new ResponseEntity<PostOffice>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<String> add(@RequestBody PostOffice postOffice) {
        try {
            service.create(postOffice);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/get_all")
    @ResponseBody
    public ResponseEntity<List<PostOffice>> get_all() {
        return new ResponseEntity<List<PostOffice>>(service.getAll(), HttpStatus.OK);
    }
}
