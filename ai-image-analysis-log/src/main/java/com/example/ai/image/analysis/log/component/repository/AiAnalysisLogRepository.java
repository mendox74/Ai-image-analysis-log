package com.example.ai.image.analysis.log.component.repository;

import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ai.image.analysis.log.component.Entity.AiAnalysisLog;

/**
 * 分類結果Repository
 */
@EnableJdbcRepositories
public interface AiAnalysisLogRepository extends CrudRepository<AiAnalysisLog, Integer> {
    @Modifying
    @Query(value = "INSERT INTO ai_analysis_log VALUES(null, :image_path, :success, :message, :class, :confidence, :request_timestamp, :response_timestamp)")
    void InsertData(@Param("image_path") String image_Path,
                    @Param("success") String success,
                    @Param("message") String message,
                    @Param("class") Integer estimated_class,
                    @Param("confidence") Double confidence,
                    @Param("request_timestamp") int request_timestamp,
                    @Param("response_timestamp") int response_timestamp);
}
