package spring.practice.zentarea.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public final class GreetingController {

    /**
     * Simple greeting endpoint
     *
     * @return ResponseEntity with greeting message
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("hehe");
    }
}