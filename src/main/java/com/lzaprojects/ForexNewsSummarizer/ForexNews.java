package com.lzaprojects.ForexNewsSummarizer;


public class ForexNews {
	private String currency;
	private String url;
	private String content;
	
	public ForexNews(String currency, String content, String url) {
		this.currency = currency;
		this.content = content;
		this.url = url;
		
	}
	
	public String getUrl() {
		return url;
	}

	public String getContent() {
		return content;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
}
