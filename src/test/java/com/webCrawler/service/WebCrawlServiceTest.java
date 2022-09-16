package com.webCrawler.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.webCrawler.service.WebCrawlerService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebCrawlServiceTest {
	
	@InjectMocks
	WebCrawlerService service;
	
	@Mock
	Jsoup jsoup;
	
	Set<String> urls = new HashSet<>();
	
	@BeforeEach
	void setUp() {
		urls.add("https://www.test.com");
		urls.add("https://www.test1.com");
		urls.add("https://www.test2.com");
	}
	
	@Test
	void getUrlsTest() {
		Mockito.when(service.getWebUrls("https://www.test.com", 1)).thenReturn(urls);
		Set<String> res=service.getWebUrls("https://www.test.com", 1);
		assertNotNull(res);
		
	}

}
