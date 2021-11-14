package br.com.dwallet.configuration;

import br.com.dwallet.queues.Queues;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = "br.com.dwallet.model")
@PropertySource("classpath:application.properties")
@ComponentScan("br.com.dwallet")
public class DigitalWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalWalletApplication.class, args);
    }

    @Bean
    public Queue tedQueue() {
        return new Queue(Queues.TED_OPERATION_QUEUE, false);
    }

    @Bean
    public Queue docQueue() {
        return new Queue(Queues.DOC_OPERATION_QUEUE, false);
    }

    @Bean
    public Queue pixQueue() {
        return new Queue(Queues.PIX_OPERATION_QUEUE, false);
    }


}
