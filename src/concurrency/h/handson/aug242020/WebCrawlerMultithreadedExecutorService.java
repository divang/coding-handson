package concurrency.h.handson.aug242020;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import concurrency.h.handson.aug242020.WebCrawlerMultithreaded.HtmlParser;

public class WebCrawlerMultithreadedExecutorService {

	public List<String> crawl(String startUrl, HtmlParser htmlParser) {
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		int index = startUrl.indexOf('/', 7);
		String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;
		Set<String> masterList = ConcurrentHashMap.newKeySet();
		List<Future<UrlCrawler>> futures = new ArrayList<>();

		submitNewUrl(startUrl, hostname, futures, masterList, htmlParser, executorService);

		while (futures.size() > 0) {
			crawlUrls(futures, hostname, masterList, htmlParser, executorService);
		}

		return new ArrayList<>(masterList);
	}

	private void crawlUrls(List<Future<UrlCrawler>> futures, String hostname, Set<String> masterList, HtmlParser htmlParser,
			ExecutorService executorService) {
		Set<UrlCrawler> urlCrawlers = new HashSet<>();

		Iterator<Future<UrlCrawler>> iterator = futures.iterator();
		while (iterator.hasNext()) {
			Future<UrlCrawler> f = iterator.next();
			if (f.isDone()) {
				iterator.remove();
				try {
					urlCrawlers.add(f.get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}

		for (UrlCrawler crawler : urlCrawlers) {
			for (String s : crawler.getUrlList()) {
				submitNewUrl(s, hostname, futures, masterList, htmlParser, executorService);
			}
		}
	}

	private void submitNewUrl(String startUrl, String hostname, List<Future<UrlCrawler>> futures, Set<String> masterList, HtmlParser htmlParser,
			ExecutorService executorService) {
		if (masterList.contains(startUrl)) {
			return;
		}

		masterList.add(startUrl);
		UrlCrawler crawler = new UrlCrawler(startUrl, htmlParser, hostname);
		futures.add(executorService.submit(crawler));
	}

	class UrlCrawler implements Callable<UrlCrawler> {
		private final String startUrl;
		private final HtmlParser htmlParser;
		private final String hostName;
		Set<String> urls = new HashSet<>();

		UrlCrawler(String startUrl, HtmlParser htmlParser, String hostName) {
			this.startUrl = startUrl;
			this.htmlParser = htmlParser;
			this.hostName = hostName;
		}

		public UrlCrawler call() {
			for (String s : htmlParser.getUrls(startUrl)) {
				if (s.startsWith(hostName)) {
					urls.add(s);
				}
			}
			return this;
		}

		public Set<String> getUrlList() {
			return urls;
		}
	}
}