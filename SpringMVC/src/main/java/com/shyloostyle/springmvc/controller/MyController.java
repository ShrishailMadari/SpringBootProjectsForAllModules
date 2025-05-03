package com.shyloostyle.springmvc.controller;

import com.shyloostyle.springmvc.service.SalesDataService;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class MyController {
    private final SalesDataService service;

    public MyController(SalesDataService service) {
        this.service = service;
    }

    @GetMapping("/read")
    @ResponseBody
    public Map<Integer,Integer> readSales(){
        return service.salesData();
    }


}
