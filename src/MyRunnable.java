@Repeat(10)
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello annotations!");
    }
}
