package dy.mebius.sample.sc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableZuulProxy
@Configuration
public class MyZuulSpringBootApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(MyZuulSpringBootApplication.class, args);
    }
}
