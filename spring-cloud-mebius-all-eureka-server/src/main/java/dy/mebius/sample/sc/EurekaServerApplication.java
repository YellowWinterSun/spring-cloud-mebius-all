package dy.mebius.sample.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动参数
 * VM option:
 * <div>
 *      -XX:+UseG1GC
 *      -XX:MaxGCPauseMillis=200
 *      -XX:InitiatingHeapOccupancyPercent=45
 *      -Xmx200M
 *      -XX:+HeapDumpOnOutOfMemoryError
 * </div>
 *
 * Program argument:
 * <div>
 *     --spring.profiles.active=cluster1
 *     --spring.profiles.active=cluster2
 * </div>
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main( String[] args ) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
