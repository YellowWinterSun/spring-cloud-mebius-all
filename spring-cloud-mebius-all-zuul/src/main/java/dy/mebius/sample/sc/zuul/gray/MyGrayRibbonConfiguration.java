package dy.mebius.sample.sc.zuul.gray;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyGrayRibbonConfiguration {

    @Bean
    public IRule myGrayMetadataRule() {
       // return new MyGrayMetadataRule();
        return new MyGrayV2Rule();
    }
}
