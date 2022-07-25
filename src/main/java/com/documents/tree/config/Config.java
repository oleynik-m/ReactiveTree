package com.documents.tree.config;


import com.documents.tree.model.Document;
import com.documents.tree.repo.DocumentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.neo4j.driver.Driver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.ReactiveDatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;

import java.util.*;

@Configuration
public class Config {

    private static final Logger logger = LogManager.getLogger(Config.class);


    @Bean
    public ReactiveNeo4jTransactionManager reactiveTransactionManager(Driver driver,
                                                                      ReactiveDatabaseSelectionProvider databaseNameProvider) {
        return new ReactiveNeo4jTransactionManager(driver, databaseNameProvider);
    }


    @Bean
    CommandLineRunner initDatabase(DocumentRepository documentRepository) {
		return args -> {
			try {
                List<Document> docs = new ArrayList<>();
                for (int i = 0, j = 100; i <= 1000 ; i++, j++) {
                    Document newDoc;
                    if (j==100) {
                        newDoc = new Document("Document" + i);
                        j = 0;
                    } else {
                        newDoc = new Document("Document" + i, docs.get(i - 1));
                    }
                    docs.add(newDoc);
                }
                documentRepository.saveAll(docs).subscribe();
            } catch (Exception e) {
				logger.error(e.getMessage());
			}
		};
	}
}
