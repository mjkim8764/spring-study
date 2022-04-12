package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.Client;
import spring.Client2;

@Configuration
public class AppCtxWithPrototype {

	/**
	 * 프로토타입 범위의 빈 설정
	 * @Scope("prototype")
	 * 일반적인 라이프사이클을 따르지 않는다. 빈 소멸 처리는 코드에서 직접 해야 함.
	 */

	@Bean
	@Scope("prototype")
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		return client;
	}

	/**
	 * 싱글톤 범위를 명시적으로 지정할 수도 있다.
	 */
	@Bean(initMethod = "connect", destroyMethod = "close")
	@Scope("singleton")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("host");
		return client;
	}
}
