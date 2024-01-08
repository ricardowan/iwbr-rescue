package cn.iwbr.rescue.grammar.thread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

/**
 * @description: 线程测试类
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @version: 3.0.0
 * @date: 2023/12/27
 */
@Slf4j
@SpringBootTest
public class ThreadTests {

    /**
     * 创建线程
     *
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     */
    @Test
    public void createThread() throws ExecutionException, InterruptedException {
        // Thread.start()是执行多线程特有方法，如果使用Thread.run()也会执行，但会以单线程方式执行。

        // 继承Thread类
        ThreadDemo td = new ThreadDemo("thread");
        td.start();

        // 实现Runnable接口
        RunnableTest runnableTest = new RunnableTest();
        Thread tt = new Thread(runnableTest, "thread1");
        tt.start();

        // 实现Callable接口，覆写call方法并使用FutureTask对象构建线程
        CallableTest callableTest = new CallableTest();
        FutureTask<String> futureTask = new FutureTask<>(callableTest);
        Thread tt2 = new Thread(futureTask, "thread3");
        tt2.start();
        System.out.println("子线程的返回值为：" + futureTask.get());

        // Callable 接口结合 ExecutorService 来创建线程
        int CPU_NUM = Runtime.getRuntime().availableProcessors();
        ThreadFactory namedThreadFactory = (new ThreadFactoryBuilder()).setNamePrefix("query-third-").build();
        ExecutorService pool = new ThreadPoolExecutor(CPU_NUM, CPU_NUM * 2, 1L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(5000), namedThreadFactory, new ThreadPoolExecutor.DiscardPolicy());
        Future<String> future = pool.submit(() -> {
            for (int i = 0; i < CPU_NUM; i++) {
                System.out.println("cpu " + i);
            }
            return "Callable thread is running";
        });
        System.out.println(future.get());

        //主线程
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程" + " is running" + i);
        }
    }

    /**
     * 线程生命周期
     *
     * @throws InterruptedException 中断异常
     */
    @Test
    public void threadLifecycle() throws InterruptedException {
        RunnableTest runnableTest = new RunnableTest();
        Thread td = new Thread(runnableTest, "stopThread");
        // 设置优先级，优先级高的在start之后拥有更大的执行run的机会
        td.setPriority(9);
        // 线程启动（多线程）
        td.start();
        // 线程运行（单线程）
        td.run();
        // 线程睡眠，不会释放锁，等时间一到立刻进入运行状态
        td.sleep(1000);
        // 线程让步，调用该方法后即可进入就绪状态，当前线程愿意放弃CPU资源，不会线程阻塞，不释放锁，不保证生效。以便更好地平衡系统资源的利用
        td.yield();
        // 线程让步，释放锁、线程阻塞，通常用于确保线程执行的顺序
        td.join();
        // 中断线程
        td.interrupt();
        // 判断线程是否存活
        td.isAlive();
        // 是否是守护线程
        td.isDaemon();
        // 将当前线程设置成守护线程
        td.setDaemon(false);
        // 设置线程的名称
        td.setName("Thread-n-a");
        // 程序中活跃的线程数
        Thread.activeCount();
        // 枚举程序中的线程
        //Thread.enumerate();
        // 得到当前线程
        Thread.currentThread();
        // 使当前线程进入等待状态，并释放对象的锁，知道调用了notify或者notifyAll后才会重新进入就绪状态等待调度
        td.wait();
        // 用于唤醒wait状态的同一个对象锁的单个线程，只会唤醒一个，至于唤醒的是哪一个取决于调度器的策略
        td.notify();
        // 用于唤醒wait状态同一个对象锁的所有线程，它会使所有处于等待状态的线程都尝试重新竞争锁，以决定哪个线程将获得锁
        td.notifyAll();
    }

    @Test
    public void stopThread(){
        RunnableTest runnableTest = new RunnableTest();
        Thread td = new Thread(runnableTest, "stopThread");
        td.start();

        ThreadDemo td1 = new ThreadDemo("thread");
        td1.start();

        //主线程
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程" + " is running" + i);
        }

        runnableTest.setStop(true);
        td1.interrupt();
    }

    /**
     * 线程同步
     */
    @Test
    public void threadSynchronisation(){

    }

    //继承Thread类
    class ThreadDemo extends Thread{
        //设置线程名称
        ThreadDemo(String name){
            super(name);
        }
        //重写run方法。
        public void run(){
            int i = 0;
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread类型线程：【{" + Thread.currentThread().getName() + "}】 is running" + i);
                i++;
            }
        }
    }

    // 实现Runnable接口
    class RunnableTest implements Runnable{
        private volatile boolean stop = false;

        @Override
        public void run() {
            int i = 0;
            while(!stop){
                System.out.println("Runnable类型线程：【{"+ Thread.currentThread().getName() +"}】 is running" + i);
                i++;
            }
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }
    }

    // 实现Callable接口
    class CallableTest implements Callable<String> {

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 5; i++){
                System.out.println("Callable类型线程：【{"+ Thread.currentThread().getName() +"}】 is running" + i);
            }
            return "Callable类型线程已运行完毕并返回：你好！";
        }
    }
}
