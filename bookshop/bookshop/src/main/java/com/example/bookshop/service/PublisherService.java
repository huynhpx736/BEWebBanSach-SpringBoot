package com.example.bookshop.service;

import com.example.bookshop.dto.PublisherDTO;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> getAllPublishers();
    PublisherDTO getPublisherById(int id);
    PublisherDTO createPublisher(PublisherDTO publisherDTO);
    PublisherDTO updatePublisher(int id, PublisherDTO publisherDTO);
    void deletePublisher(int id);
}
