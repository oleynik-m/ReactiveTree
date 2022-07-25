package com.documents.tree.component;

import com.documents.tree.repo.DocumentRepository;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.ReactiveNeo4jClient;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Additional {
    private static final Logger logger = LogManager.getLogger(Additional.class);
    @Autowired
    private ReactiveNeo4jClient client;

    @PostConstruct
    public void createIndexes() {
        try {
            client.query("CREATE INDEX node_doc_name IF NOT EXISTS FOR (n:Document) ON (n.name)").run().subscribe();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }


}
