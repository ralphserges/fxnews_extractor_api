package com.lzaprojects.ForexNewsSummarizer;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lzaprojects.ForexNewsSummarizer.FxNewsExceptionHandlers.InternalServerException;

@Service
public class SummaryResponse {
	
	@Autowired
	private SummaryResourceProperties srprop;
	
	private static final Logger logger = LoggerFactory.getLogger(SummaryResponse.class);
	

	public String getSummarizedContent(ForexNews fx)  {
		RestTemplate restTemplate = new RestTemplate();
	
		//http header
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setContentType(MediaType.APPLICATION_JSON);
		
		
		//http body with json
		JSONObject request = new JSONObject();
		request.put("content", fx.getContent());
		
		//package header and body into an httpentity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), httpHeader);
		
		String url = srprop.getDomain() +  srprop.getSubdirectory();
		logger.info(String.format("sending request to summarizer rest api at %s endpoint",url));
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
		if(response.getStatusCode() == HttpStatus.OK) {
			JSONObject jsonResp = new JSONObject(response.getBody());
			logger.info("response is successfully received from summarizer rest api");
			return jsonResp.getString("summarized_content");
		}
		
		else
			logger.error(response.getStatusCode().getReasonPhrase());
			throw new InternalServerException(response.getStatusCode(),response.getStatusCode().getReasonPhrase());
	}

}
