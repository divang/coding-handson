package concurrency.e.handson.aug292020;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BasicFutureExample {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newWorkStealingPool();

		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		callables.add(new Callable<String>() {

			@Override
			public String call() throws Exception {

				return "Call 1";
			}
		});
		callables.add(new Callable<String>() {

			@Override
			public String call() throws Exception {

				return "Call 2";
			}
		});
		callables.add(new Callable<String>() {

			@Override
			public String call() throws Exception {

				return "Call 3";
			}
		});

		List<Future<String>> results = new ArrayList<Future<String>>();
		try {
			for (Callable<String> t : callables) {
				results.add(executorService.submit(t));
			}
			// results.add(executorService.submit(callables.toArray(Future<String>)[0]));
			// List<Future<String>> results =
			// executorService.invokeAll(callables);
			for (Future<String> r : results) {
				System.out.println("r->" + r.get());
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
}
