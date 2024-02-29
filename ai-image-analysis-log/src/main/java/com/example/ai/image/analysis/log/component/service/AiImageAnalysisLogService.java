package com.example.ai.image.analysis.log.component.service;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ai.image.analysis.log.component.repository.AiAnalysisLogRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 画像分類判別Service
 */
@Service
@Slf4j
public class AiImageAnalysisLogService {

    /** 分類結果Repository */
    AiAnalysisLogRepository aiAnalysisLogRepository;
    
    public AiImageAnalysisLogService(AiAnalysisLogRepository aiAnalysisLogRepository) {
        this.aiAnalysisLogRepository = aiAnalysisLogRepository;
    }

    // 画像分類取得APIリクエストを送信するメソッド
    public Map<String, Object> sendApiRequest(String imagePath) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(imagePath, headers);
            Map<String, Object> responseJsonMap = null;

            // リクエストの送信
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(Constants.API_URL, HttpMethod.POST, entity, String.class);

            // レスポンスのJSONをMapに変換
            String strBody = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<Map<String, Object>> type = new TypeReference<Map<String, Object>>(){};
            responseJsonMap = mapper.readValue(strBody, type);
            return responseJsonMap;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("画像分類取得APIリクエストに失敗しました。", e);
            return null;
        }
    }

    // レスポンスをデータベースに保存するメソッド
    public void saveResponseToDatabase(String imagePath, Map<String, Object> responseJson, int requestTimestamp, int responceTimestamp) {
        try {
            // 分類結果を取得
            Map<String, Object> estimetedData = (Map<String,Object>) responseJson.get("estimated_data");
            // DBへ保存
            aiAnalysisLogRepository.InsertData(imagePath,
                                            String.valueOf((Boolean) responseJson.get("success")),
                                            (String) responseJson.get("message"),
                                            estimetedData.size() == 0 ? null : (int) estimetedData.get("class"),
                                            estimetedData.size() == 0 ? null : (Double) estimetedData.get("confidence"),
                                            requestTimestamp,
                                            responceTimestamp);
        } catch (Exception e) {
            log.error("分類結果の保存に失敗しました。", e);
            e.printStackTrace();
        }
    }
}
