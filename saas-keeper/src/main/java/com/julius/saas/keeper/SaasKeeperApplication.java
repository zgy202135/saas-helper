package com.julius.saas.keeper;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhougaoyang
 */
@Slf4j
@MapperScan(basePackages = "com.julius.saas.keeper.mapper")
@SpringBootApplication
public class SaasKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaasKeeperApplication.class, args);

		log.info("-------------- saas-helper application start success----------------");
	}

}
