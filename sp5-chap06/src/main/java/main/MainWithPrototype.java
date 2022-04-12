package main;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtxWithPrototype;
import spring.Client;

public class MainWithPrototype {

	public static void main(String[] args) throws IOException {
		AbstractApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppCtxWithPrototype.class);

		/**
		 * 일반적으로는 client1, client2는 같은 빈 객체를 가리키기 때문에 true 가 출력되어야 하지만,
		 * Client 타입의 client 의 이름을 갖는 빈을 프로토타입 범위의 빈으로 설정하였기 때문에
		 * getBean 메서드는 매번 새로운 객체를 생성한다.
		 * 그렇기 때문에 client1, client2는 서로 다른 객체가 된다.
		 */

		Client client1 = ctx.getBean(Client.class);
		Client client2 = ctx.getBean(Client.class);
		System.out.println("client1 == client2 : " + (client1 == client2));   // false


		ctx.close();
	}

}