package org.hannes.youtubemp3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hannes.youtubemp3.request.DownloadRequest;

public class App {
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(App.class);

	/**
	 * 
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		if (args.length != 1) {
			logger.fatal("usage: App video_id");
			System.exit(0);
		}
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.submit(new DownloadRequest(args[0])).get();
		service.shutdown();
	}
	
}