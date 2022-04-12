package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.Client;
import spring.Client2;

@Configuration
public class AppCtx {

    @Bean
    public Client client() {
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    // 빈 객체의 초기화, 소멸 과정에서 커스텀 메서드 사용
    @Bean(initMethod = "connect", destroyMethod = "close")
    public Client2 client2() {
        Client2 client = new Client2();
        client.setHost("host");
        return client;
    }
}
