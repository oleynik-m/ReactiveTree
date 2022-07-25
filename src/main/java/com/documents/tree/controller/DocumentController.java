package com.documents.tree.controller;

import com.documents.tree.model.Document;
import com.documents.tree.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/documents")
public class DocumentController {


    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PutMapping
    Mono<Document> createOrUpdateDocument(@RequestBody Document newDocument) {
        return documentService.createOrUpdateDocument(newDocument);
    }

    @GetMapping("/name/{name}")
    Mono<Document> getByName(@PathVariable String name) {
        return documentService.findByName(name);
    }


}
