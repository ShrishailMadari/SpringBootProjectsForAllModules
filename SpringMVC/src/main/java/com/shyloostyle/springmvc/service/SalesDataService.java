package com.shyloostyle.springmvc.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SalesDataService {
    public Map<Integer, Integer> salesData(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1000,3000);
        map.put(2000,5000);
        map.put(3000,6000);
        map.put(4000,9000);
        map.put(5000,7000);
        map.put(6000,4000);
        return map;
    }
}
