package com.Run;

import com.entity.Member;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentTestRun implements Runnable{

    private CountDownLatch countDownLatch;

    private Semaphore semaphore;

    private AtomicInteger integer;

    private CyclicBarrier cyclicBarrier;

    private LinkedBlockingQueue queue;

    private Member member;
    public void setCountDownLatch(CountDownLatch cd){
        countDownLatch=cd;
    }
    public void setCyclicBarrier(CyclicBarrier cb){cyclicBarrier=cb;}
    public void setSemaphore(Semaphore s){semaphore=s;}
    public void setAtomicInteger(AtomicInteger a){integer=a;}
    public void setQueue(LinkedBlockingQueue q){queue=q;}
    public void setMember(Member m){member=m;}

    public synchronized void countDownLatchDemo() {
        try {
            if(integer!=null&&countDownLatch!=null&&queue!=null){
                System.out.println("========START=========");
                int i=integer.getAndIncrement();
                Member m=new Member(i,"countDownLatchDemo"+i);
                queue.put(m);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            if(countDownLatch!=null){
                countDownLatch.countDown();
            }
        }

    }

    public synchronized void cyclicBarrierDemo() {
        try {
            if(integer!=null&&cyclicBarrier!=null&&queue!=null){
                System.out.println("========START=========");
                int i=integer.getAndIncrement();
                Member m=new Member(i,"cyclicBarrierDemo"+i);
                queue.put(m);
                cyclicBarrier.await();
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {

        }

    }

    public synchronized void semaphoreDemo() {
        try {
            if(integer!=null&&semaphore!=null&&queue!=null){
                semaphore.acquire();
                System.out.println("========START=========");
                int i=integer.getAndIncrement();
                Member m=new Member(i,"cyclicBarrierDemo"+i);
                queue.put(m);
                wait();
                System.out.println("+++++++"+Thread.currentThread().getName());

            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            semaphore.release();
        }
    }

    @Override
    public void run() {
        countDownLatchDemo();

        cyclicBarrierDemo();

        semaphoreDemo();
    }
}
