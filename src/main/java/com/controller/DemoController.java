package com.controller;

import com.Run.ConcurrentTestRun;
import com.entity.Member;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        return "{\"data\":{\"hello\":\"world\"}}";
    }

    @GetMapping("/helloGet")
    public String helloGet(@RequestParam(required = false) String param){
        if(param!=null)
            System.out.println("--------------helloGetparam-------------"+param);
        return "{\"data\":{\"hello\":\"world\"}}";
    }

    @DeleteMapping("/helloDel")
    public String helloDel(@RequestParam(required = false) String param){
        if(param!=null)
            System.out.println("--------------helloDelparam-------------"+param);
        return "{\"data\":{\"hello\":\"world\"}}";
    }

    /**
     * @RequestParam只接受值对应postman的params，@RequestBody接收d对应postman的json
     * @param param
     * @return
     */
    @PutMapping("/helloPut")
    public String helloPut(@RequestBody String param){
        if(param!=null)
            System.out.println("--------------helloPutparam-------------"+param);
        return "{\"data\":{\"hello\":\"world\"}}";
    }

    @PostMapping("/helloPost")
    public String helloPost(@RequestBody String param){
        if(param!=null)
            System.out.println("--------------helloPostparam-------------"+param);
        return "{\"data\":{\"hello\":\"world\"}}";
    }

    private volatile AtomicInteger integer=new AtomicInteger(1);

    @GetMapping("/CountDownLatchDemo")
    public void CountDownLatchDemo() throws InterruptedException {
        System.out.println("CountDownLatchDemo");
        CountDownLatch countDownLatch=new CountDownLatch(31);
        LinkedBlockingQueue queue=new LinkedBlockingQueue(31);
        for(int i=0;i<31;i++){
            ConcurrentTestRun run=new ConcurrentTestRun();
            run.setAtomicInteger(integer);
            run.setCountDownLatch(countDownLatch);
            run.setQueue(queue);
            new Thread(run).start();
        }
        countDownLatch.await();
        List<Member> list=new LinkedList<>(queue);
        for(Member m:list){
            System.out.println(m.toString());
        }

    }

    @GetMapping("/CyclicBarrierDemo")
    public void CyclicBarrierDemo() throws BrokenBarrierException, InterruptedException {
        System.out.println("CyclicBarrierDemo");
        CyclicBarrier cyclicBarrier=new CyclicBarrier(31);
        LinkedBlockingQueue queue=new LinkedBlockingQueue(31);
        for(int i=0;i<31;i++){
            ConcurrentTestRun run=new ConcurrentTestRun();
            run.setAtomicInteger(integer);
            run.setCyclicBarrier(cyclicBarrier);
            run.setQueue(queue);
            new Thread(run).start();
        }
        while(integer.intValue()<31){

        }
        List<Member> list=new LinkedList<>(queue);
        for(Member m:list){
            System.out.println(m.toString());
        }
    }

    @GetMapping("/SemaphoreDemo")
    public synchronized void SemaphoreDemo(){

        System.out.println("SemaphoreDemo");
        Semaphore semaphore=new Semaphore(3);
        LinkedBlockingQueue queue=new LinkedBlockingQueue(31);
        for(int i=0;i<31;i++){
            ConcurrentTestRun run=new ConcurrentTestRun();
            run.setAtomicInteger(integer);
            run.setSemaphore(semaphore);
            run.setQueue(queue);
            new Thread(run).start();
        }
        int i=0;
        while (i<100){
            i++;
        }
        List<Member> list=new LinkedList<>(queue);
        for(Member m:list){
            System.out.println(m.toString());
        }

        List<Member> queuesize=null;
        while(queuesize==null||queuesize.size()<31){
            notifyAll();//因为唤醒线程的锁不是wait()需要的锁，所以一直是死循环
            queuesize=new LinkedList<>(queue);
            System.out.println("------------"+queuesize.size());
        }
        for(Member m:list){
            System.out.println(m.toString());
        }
    }
}
