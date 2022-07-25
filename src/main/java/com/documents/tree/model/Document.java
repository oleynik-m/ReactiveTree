package com.documents.tree.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.INCOMING;
import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node("Document")
public class Document {
    @Id
    private String name;
    @Relationship(type = "PARENT", direction = OUTGOING)
    private Set<Document> children;
    @Relationship(type = "PARENT", direction = INCOMING)
    private Document parent;

    public Document() {}

    public Document(String name) {
        this.name = name;
    }
    public Document(String name, Document parent) {
        this.name = name;
        this.parent = parent;
    }


    public String getName() {
        return name;
    }

    public Set<Document> getChildren() {
        return children;
    }

    public void setChildren(Set<Document> children) {
        this.children = children;
    }

    public void setParent(Document parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Document{" +
                "name='" + name + '\'' +
                '}';
    }
}
