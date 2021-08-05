package com.service.Impl;

import com.entity.Member;
import com.service.ConcurrentTestServer;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ConcurrentTestServerImpl implements ConcurrentTestServer {

    private CountDownLatch countDownLatch;

    private Semaphore semaphore;

    private AtomicInteger integer;

    private CyclicBarrier cyclicBarrier;

    private Queue queue;
    public void setCountDownLatch(CountDownLatch cd){
        countDownLatch=cd;
    }
    public void setCyclicBarrier(CyclicBarrier cb){cyclicBarrier=cb;}
    public void setSemaphore(Semaphore s){semaphore=s;}
    public void setAtomicInteger(AtomicInteger a){integer=a;}
    public void setQueue(Queue q){queue=q;}
    @Override
    public void CountDownLatchDemo(Member member) {

    }

    @Override
    public void CyclicBarrierDemo(Member member) {

    }

    @Override
    public void SemaphoreDemo(Member member) {

    }
}
