package cn.iwbr.rescue.grammar.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.applet.Main;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
        // 线程睡眠
        td.sleep(1000);
        // 线程让步，当前线程愿意让出CPU的使用权，并不会使得线程进入阻塞
        td.yield();
        // 线程让步
        td.join();
        // 中断线程
        td.interrupt();
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
