package cn.iwbr.rescue.grammar.thread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @description: 线程池测试
 * @author: <a href="mailto:wangbaorui@gtmap.cn">wangbaorui</a>
 * @date: 2024/03/08
 */
public class ThreadPoolTest {

    private ThreadFactory factory = (new ThreadFactoryBuilder()).setNamePrefix("pool-thread-").build();

    /**
     * cpu数量
     */
    private int cpuNumber = Runtime.getRuntime().availableProcessors();

    @Test
    public void SingleThreadPoolTest(){
        //Executors.newSingleThreadExecutor();
        ExecutorService singlePool = Executors.newSingleThreadExecutor(factory);
        singlePool.execute(() ->{
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        singlePool.shutdown();
        System.out.println("Single Thread Pool");
    }

    @Test
    public void fixedThreadPoolTest(){
        // Executors.newFixedThreadPool(2);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2, factory);
        fixedThreadPool.execute(() ->{
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("fixed Thread Pool");
    }


    @Test
    public void cachedThreadPoolTest(){
        // Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newCachedThreadPool(factory);
        executorService.submit(() ->{
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("cached Thread Pool");
    }

    @Test
    public void scheduledThreadPoolTest(){
        //Executors.newScheduledThreadPool(2);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2, factory);
        // 延迟2秒后执行任务，并每隔3秒重复执行一次
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            System.out.println("Task is running at " + System.currentTimeMillis());
        }, 2, 3, TimeUnit.SECONDS);

        // 让主线程休眠10秒
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭线程池
        scheduledThreadPool.shutdown();
    }

    public void test(){
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setNamePrefix("pool-thread-");
        ThreadFactory factory = threadFactoryBuilder.build();

        Executors.newSingleThreadExecutor();
        Executors.newSingleThreadExecutor(factory);

        Executors.newFixedThreadPool(2);
        Executors.newFixedThreadPool(2, factory);

        Executors.newCachedThreadPool();
        Executors.newCachedThreadPool(factory);

        Executors.newScheduledThreadPool(2);
        Executors.newScheduledThreadPool(2, factory);

        ExecutorService executorService = new ThreadPoolExecutor(2, cpuNumber * 2, 5L, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(), factory, new ThreadPoolExecutor.AbortPolicy());
    }

}
