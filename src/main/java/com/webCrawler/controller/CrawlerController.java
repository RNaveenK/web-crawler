package com.webCrawler.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webCrawler.service.WebCrawlerService;

@RestController
@RequestMapping("/webCrawl/v1")
public class CrawlerController {

	@Autowired
	WebCrawlerService webCrawlerService;

	@GetMapping("/urls")
	public ResponseEntity<Set<String>> crawlWebsite(@RequestParam(required = true, value = "webUrl") String webUrl,
			@RequestParam(value = "depth", defaultValue = "0") Integer depth) {
		return new ResponseEntity<>(webCrawlerService.getWebUrls(webUrl, Integer.valueOf(depth)), HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<Set<String>> searchUrlText(@RequestParam(value = "search", required = true) String search) {
		return new ResponseEntity<>(webCrawlerService.searcUrl(search), HttpStatus.OK);
	}

}
