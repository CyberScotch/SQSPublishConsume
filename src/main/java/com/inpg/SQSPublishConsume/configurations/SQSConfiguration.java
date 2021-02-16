package com.inpg.SQSPublishConsume.configurations;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.cloud.aws.core.region.StaticRegionProvider;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SQSConfiguration {

    private String accessKey="";
    private String secretKey="";
    @Bean
    public QueueMessagingTemplate queueMessagingTemplate()
    {
        return new QueueMessagingTemplate(buildAmazonSQSAsync());
    }

    private AmazonSQSAsync buildAmazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(accessKey,secretKey)
                        )
                ).build();
    }

    @Bean
    public RegionProvider regionProvider() {
        return new StaticRegionProvider("us-east-1");
    }
}
