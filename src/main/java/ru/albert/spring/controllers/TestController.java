package ru.albert.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @GetMapping("/calc")
    public String calc(@RequestParam(value = "a", defaultValue = "1") int a,
                       @RequestParam(value = "b", defaultValue = "1") int b,
                       @RequestParam(value = "action", defaultValue = "add") String action,
                       Model model) {
        double result;

        switch (action) {
            case "add":
                result = a + b;
                break;
            case "sub":
                 result = a - b;
                 break;
            case "mul":
                 result = a * b;
                 break;
            case "div":
                 result = (double) a / b;
                 break;
            default:
                 result = 0;
                 break;
        }
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("action", action);
        model.addAttribute("result", result);



        return "test/calc";
    }
}
