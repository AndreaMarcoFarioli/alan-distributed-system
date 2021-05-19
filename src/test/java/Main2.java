public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        obj ob = new obj();
        Thread thread1 = new Thread(() -> ob.method(1));
        Thread thread2 = new Thread(() -> ob.method(2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}


class obj{
    public synchronized void  method(int a){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}