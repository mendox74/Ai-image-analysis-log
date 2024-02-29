package com.example.ai.image.analysis.log.component.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.ai.image.analysis.log.component.service.AiImageAnalysisLogService;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 画像分類判別Controller
 */
@RestController
@Slf4j
public class AiImageAnalysisLogController {

    /** 画像部品判別Service */
    private final AiImageAnalysisLogService aiImageAnalysisLogService;

    /** コンストラクタ */
    public AiImageAnalysisLogController(
        AiImageAnalysisLogService alImageAnalysisLogService) {
            this.aiImageAnalysisLogService = alImageAnalysisLogService;
        }

    /**
     * 画像Pathから分類結果を取得してDBに保存する
     */
    @PostMapping("/ai/image/analysis/log/")
    public ResponseEntity<?> AiImageAnalysisLog(@RequestBody String imagePath) {
        try {
            // APIリクエストを送信し、レスポンスを取得
            int requestTimestamp = (int) Instant.now().getEpochSecond();
            Map<String, Object> responseJson = this.aiImageAnalysisLogService.sendApiRequest(imagePath);
            int responceTimestamp = (int) Instant.now().getEpochSecond();
    
            // レスポンスをデータベースに保存
            this.aiImageAnalysisLogService.saveResponseToDatabase(imagePath, responseJson, requestTimestamp, responceTimestamp);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("画像Pathから分類結果を取得してDBに保存に失敗しました。", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}