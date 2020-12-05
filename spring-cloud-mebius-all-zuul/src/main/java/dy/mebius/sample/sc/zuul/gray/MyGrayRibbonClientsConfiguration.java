package dy.mebius.sample.sc.zuul.gray;

import org.springframework.cloud.netflix.ribbon.RibbonClients;

@RibbonClients(defaultConfiguration = MyGrayRibbonConfiguration.class)
public class MyGrayRibbonClientsConfiguration {
}
