package mk.ukim.finki.emt.advertisement.adcatalog;

import mk.ukim.finki.emt.advertisement.sharedkernel.SharedConfiguration;
import mk.ukim.finki.emt.advertisement.sharedkernel.infra.eventlog.RemoteEventLogService;
import mk.ukim.finki.emt.advertisement.sharedkernel.port.client.RemoteEventLogServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfiguration.class)
public class AdCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdCatalogApplication.class, args);
    }

    @Bean
    public RemoteEventLogService orderEvents(@Value("${app.orders.url}") String serverUrl,
                                             @Value("5000") int connectTimeout,
                                             @Value("5000") int readTimeout) {
        return new RemoteEventLogServiceClient(serverUrl, connectTimeout, readTimeout);
    }

}
