import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Parallel {
    private static final int NUM_CORES = Runtime.getRuntime().availableProcessors();

    public static <T> void For (final Iterable<T> elements, final Operation<T> operation) {
        final ExecutorService forPool = Executors.newFixedThreadPool(NUM_CORES * 2);
        try
        {
            forPool.invokeAll(createCallables(elements, operation));
        }
        catch (RejectedExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static <T> void ForEach (final Iterable<T> elements, final Operation<T> operation) {
        final ExecutorService forPool = Executors.newFixedThreadPool(NUM_CORES * 2);
        List<Future<?>> futures = new LinkedList<>();
        for (final T element : elements) {
            /*
            futures.add(forPool.submit(new Runnable() {
                @Override
                public void run() {
                    operation.perfom(element);
                }
            }));
            */
            futures.add(forPool.submit(() -> operation.perfom(element)));
        }

        for (Future<?> future : futures) {
            try
            {
                future.get();
            }
            catch (ExecutionException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        forPool.shutdown();
    }

    public static <T>Collection<Callable<Void>> createCallables(final Iterable<T> elements, final Operation<T> operation) {
        List<Callable<Void>> callables = new LinkedList<>();
        for (final T element :  elements) {
            /*
            callables.add(new Callable<Void> () {
                @Override
                public Void call() throws Exception {
                    operation.perfom(element);
                    return null;
                }
            });
            */
            callables.add(() -> {
                operation.perfom(element);
                return null;
            });
        }
        return callables;
    }
}