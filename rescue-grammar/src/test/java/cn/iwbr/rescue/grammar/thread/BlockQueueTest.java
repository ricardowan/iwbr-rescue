package cn.iwbr.rescue.grammar.thread;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

/**
 * 阻塞队列测试类
 *
 * @author
 * @date 2024/03/10
 */
@SpringBootTest
public class BlockQueueTest {

    public void priorityBlockQueueTest(){
        PriorityBlockingQueue priorityQueue = new PriorityBlockingQueue<>();

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5); // 容量为5的阻塞队列

        LinkedBlockingQueue<Integer> queue2 = new LinkedBlockingQueue<>(); // 无界阻塞队列

        SynchronousQueue synchronousQueue = new SynchronousQueue();

        new PriorityBlockingQueue<>(10, (a, b) -> {
            return 0;
        });
    }

}
