package com.example.ai.image.analysis.log.component.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

/**
 * 分類結果Entity
 */
@Data
public class AiAnalysisLog {
    @Id
    @Column("id")
    private int id;

    @Column("image_path")
    private String imagePath;

    @Column("success")
    private String success;

    @Column("message")
    private String message;

    @Column("class")
    private int estimatedClass;

    @Column("confidence")
    private Double confidence;

    @Column("request_timestamp")
    private int requestTimestamp;

    @Column("response_timestamp")
    private int responseTimestamp;
}
