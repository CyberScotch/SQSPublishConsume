package com.inpg.SQSPublishConsume.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController(value="/sqs")
public class SQSRestController {

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    /*@GetMapping("/put/{msg}")
    public void publishToSQSQueue(@PathVariable("msg") String message) {
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
    }*/

   @RequestMapping(value = "/msg", method = RequestMethod.GET)
    public void publishToSQSQueue(@RequestParam(value="mssg") String message) {
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener("SQSPublishConsumeQueue")
    public void loadMessagesFromQueue(String message) {
        System.out.println("Queue Messages: " + message);
    }

}
