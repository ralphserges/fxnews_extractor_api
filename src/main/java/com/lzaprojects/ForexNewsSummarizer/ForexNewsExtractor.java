package com.lzaprojects.ForexNewsSummarizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.lzaprojects.ForexNewsSummarizer.FxNewsExceptionHandlers.InternalServerException;
import com.lzaprojects.ForexNewsSummarizer.FxNewsExceptionHandlers.InvalidInputException;


public class ForexNewsExtractor {
	private final String weblink = "https://www.dailyfx.com/forecasts";
	private final String divClassName = "col-lg-7 col-xl-8 dfx-border--r-lg-1";
	private Map<String,String> fxNewsLinkMap; // map with key as currency and values as fx new weblink
	private static final Logger logger = LoggerFactory.getLogger(ForexNewsExtractor.class);
	
	public ForexNewsExtractor() {
		this.fxNewsLinkMap = getFxNewsLinksMap();
	}
	
	public Map<String,ForexNews> getAllFxNews() {
		
		Map<String,ForexNews> fxNews= new HashMap<String,ForexNews>();
		
		for (Map.Entry<String,String> entry : fxNewsLinkMap.entrySet()) {
			ForexNews fx = getSpecifiedFxNews(entry.getKey());
			fxNews.put(entry.getKey(),fx);
			
		}
		
		return fxNews;
	}
	
	
	public ForexNews getSpecifiedFxNews(String currency)  {
		
	
		if(fxNewsLinkMap.containsKey(currency)) {
			String content = getForexContentFromLink(fxNewsLinkMap.get(currency));
			ForexNews fx = new ForexNews(currency,content,fxNewsLinkMap.get(currency));
			logger.info(String.format("Extracted %s news.",currency));
		
			return fx;
		}
				
		else {
			logger.error(String.format("%s news is not found",currency));
			throw new InvalidInputException(HttpStatus.NOT_FOUND,"Currency not found.");
		}
			
	}
	
	
	public List<String> getFxCurrencies()  {
		 
		List<String> fxCurrencies = new ArrayList<String>();
		
		for (Map.Entry<String,String> entry : fxNewsLinkMap.entrySet()) {
			fxCurrencies.add(entry.getKey());
		}
		
		logger.info(String.format("%s are available for reading", fxCurrencies.toString()));
		return fxCurrencies;
	}
	
	
	// extract links from the div and store to map where key is the currency name and value is the news link
	private Map<String, String> getFxNewsLinksMap() {
		Map<String,String> fxNewsMap = new HashMap<String,String>();
		Element extractedDiv = getDivByClass();
		
		for(Element childElement : extractedDiv.children()) {
			if(!childElement.className().equals("mt-1 mb-6 py-2 hidden-print")) {
				String currency = childElement.getElementsByTag("h2").text().replaceAll(" ", "-").toLowerCase();
				String fxLink = childElement.getElementsByTag("a").attr("href");
				fxNewsMap.put(currency, fxLink);
				
			}
		}
		return fxNewsMap;
	}
	
	
	// extract content from given link
	private String getForexContentFromLink(String link)  {
		try {
			
			Document doc = Jsoup.connect(link).get();
			Elements contents = doc.getElementsByClass("gsstx");
			
			StringBuilder sb = new StringBuilder();
			for (Element e : contents) {
				sb.append(e.text());
			}
			
			return sb.toString();
			
		} catch (Exception e1) {
			logger.error(String.format("Error occured when extracting content from %s.",link));
			throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please contact admin at 12345678.");
		}
	}
	
	
	// get div that contains all forex news and its link
	private Element getDivByClass() {
		
		try {
			
			logger.info(String.format("Web scrapping data from %s.",weblink));
			Document doc = Jsoup.connect(weblink).get(); 
			Elements content = doc.getElementsByClass(divClassName);
			return content.get(0);
		} catch (Exception e) {
			
			// create custom exception to handle 
			logger.error(String.format("Error occured when web scrapping data from %s.",weblink));
			throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error. Please contact admin at 12345678.");
		}
		
	}
}


