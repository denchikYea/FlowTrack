package by.kolp.tasks.controller;

import by.kolp.tasks.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/calculator")
public class CalculateServiceController {

    @Autowired
    private CalculateService service;



}
