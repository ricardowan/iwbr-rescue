package cn.iwbr.rescue.grammar.thread;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 线程锁测试类
 *
 * @author ricardowang
 * @date 2024/03/07
 */
@SpringBootTest
public class LockTests {

    private int count = 0;

    /**
     * 基本数据类型局部变量
     */
    private int threadNum = 10;

    /**
     * 引用类型局部变量
     */
    private String name = "ThreadTests";

    /**
     * 原子变量
     */
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    // 重入锁
    private Lock lock = new ReentrantLock();

    // 读写锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 信号量
    private Semaphore semaphore = new Semaphore(2);

    // 倒计时锁存器
    private CountDownLatch latch = new CountDownLatch(12);

    // 循环屏障
    CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        System.out.println("所有线程：都达到屏障点！");
        System.out.println("此时count的值为：" + count);
    });

    @Test
    public void synchronizedTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (name) {
                try {
                    name.wait();
                    System.out.println("waiting...");
                } catch (InterruptedException e) {
                    System.out.println("error...");
                }
            }
        });
        thread.start();

        Thread.sleep(100);
        synchronized (name) {
            System.out.println("main...");
            name.notify();
        }
    }

    @Test
    public void reentrantLockTest() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                reentrantLockCount();
                System.out.println(Thread.currentThread().getName() + "执行完毕，count值为：" + count);
            }, "lock-thread-" + i).start();
        }
    }

    private void reentrantLockCount() {
        try {
            lock.lock();
            count++;
            count++;
        } catch (Exception e) {
            System.out.println("error...");
        } finally {
            lock.unlock();
        }
    }

    @Test
    public void readWriteLockTest() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                readWriteLockCount();
                System.out.println(Thread.currentThread().getName() + "执行完毕，count值为：" + count);
            }, "lock-thread-" + i).start();
        }
    }

    private void readWriteLockCount() {
        try {
            readWriteLock.readLock().lock();
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + "读！");
        } catch (Exception e) {
            System.out.println("error...");
        } finally {
            readWriteLock.readLock().unlock();
        }

        try {
            readWriteLock.writeLock().lock();
            count++;
            count++;
            count++;
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + "写！");
        } catch (Exception e) {
            System.out.println("error...");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Test
    public void atomicTest() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    while (!atomicAddCount()) {
                        System.out.println(Thread.currentThread().getName() + "-" + j + "-" + atomicInteger.get() + "旧值与地址中的值不一致，不会进行增加操作！");
                        break;
                    }
                }
            }, "lock-thread-" + i).start();
        }
        // 等待所有线程完成
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出最终结果
        System.out.println("Final value: " + atomicInteger.get());
    }

    private boolean atomicAddCount() {
        int oldValue = atomicInteger.get();
        int newValue = oldValue + 1;
        return atomicInteger.compareAndSet(oldValue, newValue);
    }

    @Test
    public void semaphoreTest() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                semaphoreCount();
            }, "lock-thread-" + i).start();
        }

        // 等待所有线程完成
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

    private void semaphoreCount() {
        try {
            semaphore.acquire(); // 获取许可证
            System.out.println(Thread.currentThread().getName() + " is accessing resource.");
            count++;
            count++;
            count++;
            System.out.println(Thread.currentThread().getName() + "执行完成，此时count为：" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // 释放许可证
            System.out.println(Thread.currentThread().getName() + " has released resource.");
        }
    }

    @Test
    public void countDownLatchTest() throws InterruptedException {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                countDownLatchCount();
            }, "lock-thread-" + i).start();
        }

        latch.await(1000, TimeUnit.MILLISECONDS);
        System.out.println("count：" + count);
    }

    private void countDownLatchCount() {
        try {
            count++;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            // 每个线程完成后计数器减一
            latch.countDown();
        }
    }

    @Test
    public void cyclicBarrierTest() {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                cyclicBarrierCount();
            }, "lock-thread-" + i).start();
        }
    }

    private void cyclicBarrierCount() {
        // 模拟线程工作
        try {
            count++;
            count++;
        } catch (Exception e) {
            System.out.println("error...");
        }

        try {
            // 等待其他线程到达屏障
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
