public class RepeatAnnotationTest {
    public static void main(String[] args) {
            CustomThreadPoolExecutor customThreadPoolExecutor =
                    new CustomThreadPoolExecutor(10);
            customThreadPoolExecutor.execute(new MyRunnable());
            customThreadPoolExecutor.shutdown();
    }
}
