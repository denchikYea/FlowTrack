package by.kolp.tasks.controller;

import by.kolp.tasks.model.dto.NumericDataEntryDTO;
import by.kolp.tasks.service.CalculateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/calculator")
public class CalculateServiceController {

    @Autowired
    private CalculateService service;


    public String showForm(Model model) {
        model.addAttribute("numericDataEntry", new NumericDataEntryDTO());
        return "calculation"; //html

    }


    @PostMapping("/calculate")
    public String calculateAll(@ModelAttribute("numericDataEntry") NumericDataEntryDTO data, Model model ) {

        Integer result = service.sumAllValues(data);
        model.addAttribute("result", result);
        return "redirect:/results";
    }

}
