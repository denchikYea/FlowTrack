package by.kolp.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FinanceController {

    @GetMapping
    @ResponseBody
    public String helloMsg(){
        return "Hello world!";
    }


}
