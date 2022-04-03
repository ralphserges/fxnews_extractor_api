# fxnews_extractor_api

This is a Restful API service that provides user the ability to read summarized forex-related news of the highly traded currencies where original contents are webscrapped from www.dailyfx.com , 
send to another Restful API for content summary service. (https://github.com/ralphserges/fx_summarizer_api.git) and then returns result packaged in JSON format. 


Baseurl : http://lzafxnewsextractor-env.eba-fmmcruxj.us-east-1.elasticbeanstalk.com/lza-projects/fxsummarizer
* for listing the names of all currencies with readily available news: {Baseurl}/currencies-list 
* for reading a summarized version of a specified currency forex news: {Baseurl}/summaries/{currency} 
* for reading summarized version of all currencies forex news: {Baseurl}/summaries

