package com.hsartori.spring.web.testing;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spring")
public class DummyController {

    private String value = "0";

    @GetMapping("/value")
    public String get() {
        return value;
    }

    @PostMapping("/value")
    public String post(@RequestBody final String value) {
        return this.value = value;
    }


}
