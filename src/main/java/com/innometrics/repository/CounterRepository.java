package com.innometrics.repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by s_lor_000 on 10/8/2015.
 */
@Repository
public class CounterRepository {

    private ConcurrentMap<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

    public ConcurrentMap<String, AtomicInteger> getCounterMap() {
        return counterMap;
    }

    public void setCounterMap(ConcurrentMap<String, AtomicInteger> counterMap) {
        this.counterMap = counterMap;
    }
}
