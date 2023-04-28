package ru.mediasoft.test.MediaSoftTest.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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

import ru.mediasoft.test.MediaSoftTest.models.History;
import ru.mediasoft.test.MediaSoftTest.models.Package;
import ru.mediasoft.test.MediaSoftTest.services.HistoryService;
import ru.mediasoft.test.MediaSoftTest.services.PackageService;
import ru.mediasoft.test.MediaSoftTest.utils.PackageState;

@RestController
@RequestMapping(value = "/package/*")
public class PackageController {
    private final PackageService service;
    private final HistoryService historyService;

    @Autowired
    public PackageController(PackageService service, HistoryService historyService) {
        this.service = service;
        this.historyService = historyService;
    }

    @GetMapping(value = "/get_info")
    @ResponseBody
    public ResponseEntity<Package> get_info(@RequestParam("id") int id) {
        if (service.exists(id)) {
            return new ResponseEntity<Package>(service.get(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<Package>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get_all")
    @ResponseBody
    public ResponseEntity<List<Package>> get_all() {
        return new ResponseEntity<List<Package>>(service.getAll(),HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody Package pckg,
            @RequestParam("post_office_id") int post_office_id) {
        try {
            Package p = service.create(pckg).get();

            History history = new History();
            history.setPackageId(p.getId());
            history.setPostOfficeId(post_office_id);
            history.setArrivalDate(new Timestamp(System.currentTimeMillis()));
            history.setPackageState(PackageState.Transfered);
            historyService.create(history);
            return new ResponseEntity<String>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/leave")
    @ResponseBody
    public ResponseEntity<String> leave(@RequestParam("package_id") int package_id) {
        try {
            Optional<History> history = historyService.findLastByPackageId(package_id);

            if (history.isPresent()) {
                history.get().setLeaveDate(new Timestamp(System.currentTimeMillis()));
                historyService.update(history.get());
                return new ResponseEntity<String>(HttpStatus.OK);
            }

            return new ResponseEntity<String>("Record not found in history", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/arrived")
    @ResponseBody
    public ResponseEntity<String> arrived(@RequestParam("package_id") int package_id,
            @RequestParam("post_office_id") int post_office_id) {
        try {
            if (historyService.findLastByPackageId(package_id).get().getLeaveDate() == null) {
                return new ResponseEntity<String>(
                        "Package is not left current post office. Please set left status to continue.",
                        HttpStatus.CONFLICT);
            }
            History history = new History();
            history.setArrivalDate(new Timestamp(System.currentTimeMillis()));
            history.setPackageId(package_id);
            history.setPostOfficeId(post_office_id);

            Package pckg = service.get(package_id).get();
            if (pckg.getRecipientIndex() == post_office_id) {
                history.setPackageState(PackageState.Received);
            } else {
                history.setPackageState(PackageState.InDelivery);
            }

            historyService.create(history);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
