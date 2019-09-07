package org.pcl.springlongkuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(value = "org.pcl.springlongkuang.Mapper")
public class SpringlongkuangApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringlongkuangApplication.class, args);
	}

}
