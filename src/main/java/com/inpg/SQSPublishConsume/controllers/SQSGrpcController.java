package com.inpg.SQSPublishConsume.controllers;

import com.sqspublish.PublisherProto.PublisherPostReq;
import com.sqspublish.PublisherProto.PublisherPostRes;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;



@GrpcService
public class SQSGrpcController extends com.sqspublish.PublisherProto.PublisherServiceGrpc.PublisherServiceImplBase {

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endPoint;

    public void postPublisherPayload(PublisherPostReq request, StreamObserver<PublisherPostRes> responseObserver)
    {
        String messageBody=request.getPublisherName()+request.getPublisherMessage();
        queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(messageBody).build());

        PublisherPostRes.Builder response=PublisherPostRes.newBuilder().setAck("Payload pushed to SQS queue");
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
}

