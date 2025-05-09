package by.kolp.tasks.controller;

import by.kolp.tasks.entity.NumericDataEntry;
import by.kolp.tasks.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculator")
public class CalculateServiceController {

    @Autowired
    private CalculateService service;



    public String showForm(Model model) {
        model.addAttribute("numericDataEntry", new NumericDataEntry());
        return "calculation"; //html

    }


    @PostMapping("/calculate")
    public String calculate(@ModelAttribute("numericDataEntry") NumericDataEntry data, Model model) {

        String key = data.getKey();
        Integer value = data.getValue();

        //TODO

        return null;
    }

}
