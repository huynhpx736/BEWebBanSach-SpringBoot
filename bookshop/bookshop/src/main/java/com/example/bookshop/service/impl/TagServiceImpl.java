package com.example.bookshop.service.impl;

import com.example.bookshop.dto.TagDTO;
import com.example.bookshop.entity.Tag;
import com.example.bookshop.mapper.TagMapper;
import com.example.bookshop.repository.TagRepository;
import com.example.bookshop.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO getTagById(int id) {
        return tagRepository.findById(id)
                .map(tagMapper::toDTO)
                .orElse(null);
    }

    @Override
    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = tagMapper.toEntity(tagDTO);
        return tagMapper.toDTO(tagRepository.save(tag));
    }

    @Override
    public TagDTO updateTag(int id, TagDTO tagDTO) {
        if (tagRepository.existsById(id)) {
            Tag tag = tagMapper.toEntity(tagDTO);
            tag.setId(id);
            return tagMapper.toDTO(tagRepository.save(tag));
        }
        return null;
    }

    @Override
    public void deleteTag(int id) {
        tagRepository.deleteById(id);
    }
}
