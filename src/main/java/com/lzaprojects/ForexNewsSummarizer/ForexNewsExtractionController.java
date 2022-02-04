package com.lzaprojects.ForexNewsSummarizer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value="lza-projects/fxsummarizer")
public class ForexNewsExtractionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ForexNewsExtractionController.class);
	
	@Autowired
	private SummaryResponse sr;
	
	// users to get all currencies with available fx news 
	@RequestMapping(value="/currencies-list", method=RequestMethod.GET)
	public List<String> getCurrencyList() {
		logger.info("Client requested for a list of fx currencies news available for reading");
		ForexNewsExtractor fxExtractor = new ForexNewsExtractor();
		return fxExtractor.getFxCurrencies();
	}
	
	//user to get specified currency fx new
	@RequestMapping(value="/summaries/{currency}", method=RequestMethod.GET)
	public ForexNews getForexNews(@PathVariable String currency) {
		logger.info("Client requested for a specified fx currency summarized news");
		ForexNewsExtractor fxExtractor = new ForexNewsExtractor();
		ForexNews fx = fxExtractor.getSpecifiedFxNews(currency);
		
		String summarizedContent = sr.getSummarizedContent(fx);
		fx.setContent(summarizedContent);
		
		return fx;
		
	}
	
	//user to get all currency fx news
	@RequestMapping(value="/summaries", method=RequestMethod.GET)
	public  Map<String,ForexNews> getForexNews() {
		logger.info("Client requested for all fx currency summarized news");
		ForexNewsExtractor fxExtractor = new ForexNewsExtractor();
		Map<String,ForexNews> fxNews = fxExtractor.getAllFxNews();
		
		Map<String,ForexNews> summarizedNews = new HashMap<String,ForexNews>();
		StringBuilder summarizedContent;
		
		for (Map.Entry<String,ForexNews> entry : fxNews.entrySet()) {
			
			summarizedContent = new StringBuilder(sr.getSummarizedContent(entry.getValue()));
			entry.getValue().setContent(summarizedContent.toString());
			summarizedNews.put(entry.getKey(), entry.getValue());
			 
		}
		
		return fxNews;
	}
	
	
}

