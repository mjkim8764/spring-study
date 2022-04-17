package config;

/**
 * Tomcat JDBC 모듈은 DB Connection pool 기능을 제공한다.
 */

import org.apache.tomcat.jdbc.pool.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.MemberDao;

@Configuration
public class AppCtx {

    @Bean(destroyMethod = "close")    // Bean 객체 소멸 과정에서 사용할 메서드 이름 : close
    public DataSource dataSource() {

        // Tomcat JDBC 모듈은 javax.sql.DataSource 를 구현한 DataSource 클래스를 제공
        DataSource ds = new DataSource();

        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
        ds.setUsername("root");
        ds.setPassword("1234");
        ds.setInitialSize(2);   // setInitialSize : Connection Pool 을 초기화할 때 생성할 초기 Connection 수, default : 10
        ds.setMaxActive(10);    // setMaxActive :  Pool 에서 가져올 수 있는 최대 Connection 수, default : 100
        ds.setTestWhileIdle(true);    // 유휴 커넥션 검사
        ds.setMinEvictableIdleTimeMillis(60000 * 3);      // 최소 유휴 시간 3분
        ds.setTimeBetweenEvictionRunsMillis(10 * 1000);     // 주기 : 10초 설정

        return ds;
    }

    @Bean
    public MemberDao memberDao(){
        return new MemberDao(dataSource());
    }
}
