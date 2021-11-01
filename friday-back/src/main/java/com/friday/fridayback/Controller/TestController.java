package com.friday.fridayback.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/test")
public class TestController {

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public record TestRecord(String name, int number){
    }

    @RequestMapping(value = "/okString", method = RequestMethod.GET)
    public String okString(){
        return "ok";
    }

    @RequestMapping(value = "/okJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String okJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var testReturn = new ArrayList<TestRecord>();
        testReturn.add(new TestRecord("toto",1));
        testReturn.add(new TestRecord("tata",2));
        return mapper.writeValueAsString(testReturn);
    }
}

