package com.example.bookshop.service;

import com.example.bookshop.dto.TagDTO;

import java.util.List;

public interface TagService {
    List<TagDTO> getAllTags();
    TagDTO getTagById(int id);
    TagDTO createTag(TagDTO tagDTO);
    TagDTO updateTag(int id, TagDTO tagDTO);
    void deleteTag(int id);
}

