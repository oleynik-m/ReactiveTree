package com.documents.tree.repo;

import com.documents.tree.model.Document;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface DocumentRepository extends ReactiveNeo4jRepository<Document, String> {
    Mono<Document> findOneByName(String name);
}
