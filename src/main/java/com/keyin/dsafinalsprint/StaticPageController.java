package com.keyin.dsafinalsprint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {
    @GetMapping("/enter-numbers")
    public String enterNumbersPage() {
        return "enter-numbers.html";
    }
}
