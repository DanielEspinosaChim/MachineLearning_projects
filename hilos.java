
public class hilos {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread(5, 500));
        Thread t2 = new Thread(new MyThread(5, 500));
        Thread t3 = new Thread(new MyThread(5, 500));

        t1.setName("Thread one");
        t2.setName("Thread two");
        t3.setName("Thread three");

        t1.start(); 
        t2.start();
        t3.start();
    
        
        
    }
}

class MyThread implements Runnable {
    int maxCount, sleepTime;
    public MyThread(int c, int t) {
        maxCount = c;
        sleepTime = t;
    }
    public void run() {
        for (int i = 0; i < maxCount; ++i) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {}
        }
    }
}
