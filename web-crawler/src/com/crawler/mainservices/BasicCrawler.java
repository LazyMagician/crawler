package com.crawler.mainservices;
import org.apache.log4j.Logger;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class BasicCrawler {
	
	private static final Logger logger = Logger.getLogger( BasicCrawler.class );
	public static void main(String[] args) {
		crawl(1, "https://www.google.com/search?q=MODULE%2C7.5KW%2C6SL3225-0BE25-5AA1%2CSIEMENS", new ArrayList<String>());
	}
	
	
	private static Document requestData(String url, ArrayList<String> visitedUrls) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			if(con.response().statusCode() == 200) {
//				System.out.println("Link :"+url);
//				System.out.println("Doc :"+doc.title());
				visitedUrls.add(url);
				return doc;
			}
			System.out.println("Connection lost "+con.response().statusCode());
			return null;
		} catch (Exception e) {
			logger.error("Something went wrong while requesting to "+url+" ....",e);
//			System.exit(0);
		}
		
		return null;
	}
	
	
	private static void crawl(int level,String url,ArrayList<String> visitedUrls) {
		String xpath = "//* [starts-with(@jsname, \"UWckNb\")]";
		if ( level <= 10 ) {
			try {
			  Document doc = requestData(url, visitedUrls);
//			  System.out.println(doc.selectXpath(xpath));
			  
//   Find all the href tags and add to the visited list 
//			  if (doc != null) {
//				  for(Element link : doc.select("a[href]")) {
//					  String nextLink = link.absUrl("href");
//					  if(!visitedUrls.contains(nextLink)) {
//						  crawl(level++, nextLink, visitedUrls);
//					  }
//				  }
//			  }
			  String text = doc.body().text();
			  System.out.println("*********************** \n"+text+"\n*********************** \n");
			  if(doc != null) {
				  for(Element link: doc.selectXpath(xpath)) {
					  String nextLink = link.absUrl("href");
					  if(!visitedUrls.contains(nextLink)) {
						  crawl(level++, nextLink, visitedUrls);
					  }
				  }
			  }
			} catch (Exception e) {
				logger.error("Something went wrong while crawling on "+url+" ....",e);
//				System.exit(0);
			}			
		}
		logger.info("Level completed successfully ...");
	}

}
