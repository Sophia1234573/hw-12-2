import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@interface Repeat {
    int value();
}

class CustomThreadPoolExecutor extends ThreadPoolExecutor {
    
    public CustomThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable != null) {
            Class<? extends Runnable> runnableClass = runnable.getClass();
            Repeat repeat = runnableClass.getAnnotation(Repeat.class);
            if (repeat == null) {
                super.execute(runnable);
            } else {
                for (int i = 0; i < repeat.value(); i++) {
                    super.execute(runnable);
                }
            }
        }
    }
}
