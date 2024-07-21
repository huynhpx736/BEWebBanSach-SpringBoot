package com.example.bookshop.controller;

import com.example.bookshop.dto.PublisherDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllPublishers() {
        List<PublisherDTO> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(new ResponseData(200, "Success", publishers, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getPublisherById(@PathVariable int id) {
        PublisherDTO publisher = publisherService.getPublisherById(id);
        if (publisher != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", publisher, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Publisher not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        PublisherDTO createdPublisher = publisherService.createPublisher(publisherDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Publisher created", createdPublisher, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updatePublisher(@PathVariable int id, @RequestBody PublisherDTO publisherDTO) {
        PublisherDTO updatedPublisher = publisherService.updatePublisher(id, publisherDTO);
        if (updatedPublisher != null) {
            return ResponseEntity.ok(new ResponseData(200, "Publisher updated", updatedPublisher, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Publisher not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.ok(new ResponseData(200, "Publisher deleted", null, true));
    }
}

