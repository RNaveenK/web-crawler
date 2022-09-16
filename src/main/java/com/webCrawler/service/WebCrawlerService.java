package com.webCrawler.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class WebCrawlerService {

	private Set<String> urls = new HashSet<>();
	private static final int MAX_DEPTH = 3;

	public Set<String> getWebUrls(String webUrl, Integer depth) {
		System.out.println("Crawling : [" + webUrl + "]");
		if (!urls.contains(webUrl) && (depth < MAX_DEPTH)) {
			System.out.println("Depth: " + depth + " [" + webUrl + "]");
			//TODO: store the urls to a DB instead of using global set
			urls.add(webUrl);
			getLinks(webUrl, depth);
		}
		return urls;
	}

	private void getLinks(String webUrl, Integer depth) {
		Document doc;
		try {
			doc = Jsoup.connect(webUrl).get();
			Elements availableLinks = doc.select("a[href]");
			depth++;
			for (Element link : availableLinks) {
				getWebUrls(link.attr("abs:href"), depth);
			}
		} catch (IOException e) {
			System.err.println("Exception for '" + webUrl + "': " + e.getMessage());
		}

	}

	public Set<String> searcUrl(String search) {
		if (urls.isEmpty())
			return new HashSet<>();
		
		return urls.parallelStream().filter(s -> s.contains(search)).collect(Collectors.toSet());
	}

}
