//package com.techelevator.controller;
//
//import com.techelevator.model.Message;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@PreAuthorize("isAuthenticated()")
//@CrossOrigin
//public class MessageController {
//
//    @RequestMapping(path = "/message", method = RequestMethod.GET)
//    public Message getMessage() {
//        Message message = new Message();
//        message.setText("hello there");
//        message.setDate(new Date());
//        return message;
//    }
//}
