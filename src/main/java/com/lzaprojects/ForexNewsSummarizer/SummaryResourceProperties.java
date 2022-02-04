package com.lzaprojects.ForexNewsSummarizer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="summaryresource")
public class SummaryResourceProperties {
	
	private String domain;
	private String port;
	private String subdirectory;

	
	public String getDomain() {
		return domain;
	}

	public String getPort() {
		return port;
	}

	public String getSubdirectory() {
		return subdirectory;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setSubdirectory(String subdirectory) {
		this.subdirectory = subdirectory;
	}

}
