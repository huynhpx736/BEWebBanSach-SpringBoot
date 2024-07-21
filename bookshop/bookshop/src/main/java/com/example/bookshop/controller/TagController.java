package com.example.bookshop.controller;

import com.example.bookshop.dto.TagDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(new ResponseData(200, "Success", tags, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getTagById(@PathVariable int id) {
        TagDTO tag = tagService.getTagById(id);
        if (tag != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", tag, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Tag not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createTag(@RequestBody TagDTO tagDTO) {
        TagDTO createdTag = tagService.createTag(tagDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Tag created", createdTag, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateTag(@PathVariable int id, @RequestBody TagDTO tagDTO) {
        TagDTO updatedTag = tagService.updateTag(id, tagDTO);
        if (updatedTag != null) {
            return ResponseEntity.ok(new ResponseData(200, "Tag updated", updatedTag, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Tag not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteTag(@PathVariable int id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok(new ResponseData(200, "Tag deleted", null, true));
    }
}

