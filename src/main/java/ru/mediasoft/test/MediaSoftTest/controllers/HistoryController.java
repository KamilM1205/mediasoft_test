package ru.mediasoft.test.MediaSoftTest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.mediasoft.test.MediaSoftTest.models.History;
import ru.mediasoft.test.MediaSoftTest.services.HistoryService;

@Controller
@RequestMapping(value = "/history/*")
public class HistoryController {
    private final HistoryService service;

    @Autowired
    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/get_all_by_package_id")
    @ResponseBody
    public ResponseEntity<List<History>> getAllByPackageId(@RequestParam("id") int package_id) {
        return new ResponseEntity<List<History>>(service.findAllByPackageId(package_id), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseEntity<String> add(@RequestBody History history) {
        try {
            service.create(history);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
