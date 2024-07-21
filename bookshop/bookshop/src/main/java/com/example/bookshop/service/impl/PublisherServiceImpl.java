package com.example.bookshop.service.impl;

import com.example.bookshop.dto.PublisherDTO;
import com.example.bookshop.entity.Publisher;
import com.example.bookshop.mapper.PublisherMapper;
import com.example.bookshop.repository.PublisherRepository;
import com.example.bookshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(publisherMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherDTO getPublisherById(int id) {
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElse(null);
    }

    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        return publisherMapper.toDTO(publisherRepository.save(publisher));
    }

    @Override
    public PublisherDTO updatePublisher(int id, PublisherDTO publisherDTO) {
        if (publisherRepository.existsById(id)) {
            Publisher publisher = publisherMapper.toEntity(publisherDTO);
            publisher.setId(id);
            return publisherMapper.toDTO(publisherRepository.save(publisher));
        }
        return null;
    }

    @Override
    public void deletePublisher(int id) {
        publisherRepository.deleteById(id);
    }
}

