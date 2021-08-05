package com.service;

import com.entity.Member;

public interface ConcurrentTestServer {

    public void CountDownLatchDemo(Member member);

    public void CyclicBarrierDemo(Member member);

    public void SemaphoreDemo(Member member);
}
