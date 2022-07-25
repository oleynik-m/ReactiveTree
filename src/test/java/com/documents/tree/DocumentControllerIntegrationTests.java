package com.documents.tree;

import com.documents.tree.controller.DocumentController;
import com.documents.tree.model.Document;
import com.documents.tree.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = DocumentController.class, excludeAutoConfiguration = ReactiveSecurityAutoConfiguration.class)
public class DocumentControllerIntegrationTests {
    @MockBean
    private DocumentService documentService;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void shouldGetDocument() throws Exception {
        Document document = new Document("Document");

        Mono<Document> doc = Mono.just(document);
        given(this.documentService.findByName("Document"))
                .willReturn(doc);

        this.webClient.get().uri("/documents/name/{name}","Document").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("{\"name\":\"Document\",\"children\":null}");

        verify(documentService,times(1)).findByName(anyString());
    }

    @Test
    void testCreateEmployee() {
        Document document = new Document("Document");

        Mono<Document> doc = Mono.just(document);

        given(this.documentService.createOrUpdateDocument(document))
                .willReturn(doc);

        webClient.put()
                .uri("/documents")
                .contentType(MediaType.APPLICATION_JSON)
                .body(doc,Document.class)
                .exchange()
                .expectStatus().isOk();

        verify(documentService,times(1)).createOrUpdateDocument(any());
    }

}
