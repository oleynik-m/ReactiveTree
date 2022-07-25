package com.documents.tree.service;

import com.documents.tree.model.Document;
import com.documents.tree.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Mono<Document> createOrUpdateDocument(Document newDocument) {
        return documentRepository.save(newDocument);
    }

    public Mono<Document> findByName(String name) {
        return documentRepository.findOneByName(name);
    }

}
