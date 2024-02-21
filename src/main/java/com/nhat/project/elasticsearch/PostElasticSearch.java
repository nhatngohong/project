package com.nhat.project.elasticsearch;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostElasticSearch {
    @Id
    private String id;

    private String title;

    private String content;
}
