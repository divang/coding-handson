package concurrency.h.handson.aug242020;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WebCrawlerMultithreaded {

	public List<String> crawl(String startUrl, HtmlParser htmlParser) {

		// find hostname
		int index = startUrl.indexOf('/', 7);
		String hostname = (index != -1) ? startUrl.substring(0, index) : startUrl;

		// multi-thread
		Crawler crawler = new Crawler(startUrl, hostname, htmlParser);
		crawler.map = new ConcurrentHashMap<>();
		crawler.result = crawler.map.newKeySet();
		Thread thread = new Thread(crawler);
		thread.start();

		crawler.joinThread(thread); // wait for thread to complete
		return new ArrayList<>(crawler.result);
	}

	interface HtmlParser {
		public List<String> getUrls(String url);
	}

	static class Crawler implements Runnable {
		String startUrl;
		String hostname;
		HtmlParser htmlParser;
		public static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
		public static Set<String> result = map.newKeySet();

		public Crawler(String startUrl, String hostname, HtmlParser htmlParser) {
			this.startUrl = startUrl;
			this.hostname = hostname;
			this.htmlParser = htmlParser;
		}

		@Override
		public void run() {
			if (this.startUrl.startsWith(hostname) && !this.result.contains(this.startUrl)) {
				this.result.add(this.startUrl);
				List<Thread> threads = new ArrayList<>();
				for (String s : htmlParser.getUrls(startUrl)) {
					if (result.contains(s))
						continue;
					Crawler crawler = new Crawler(s, hostname, htmlParser);
					Thread thread = new Thread(crawler);
					thread.start();
					threads.add(thread);
				}
				for (Thread t : threads) {
					joinThread(t); // wait for all threads to complete
				}
			}
		}

		public static void joinThread(Thread thread) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
