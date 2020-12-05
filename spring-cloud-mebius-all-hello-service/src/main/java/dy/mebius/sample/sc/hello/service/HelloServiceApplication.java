package dy.mebius.sample.sc.hello.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动参数
 * VM option:
 * <div>
 *      -XX:+UseG1GC
 *      -XX:MaxGCPauseMillis=200
 *      -XX:InitiatingHeapOccupancyPercent=45
 *      -Xmx512M
 *      -XX:+HeapDumpOnOutOfMemoryError
 * </div>
 *
 * Program argument:
 * <div>
 *     --server.port=8081
 * </div>
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class HelloServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloServiceApplication.class, args);
    }
}
