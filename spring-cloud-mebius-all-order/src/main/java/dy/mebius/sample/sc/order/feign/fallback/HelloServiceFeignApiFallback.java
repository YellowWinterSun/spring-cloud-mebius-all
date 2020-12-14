package dy.mebius.sample.sc.order.feign.fallback;

import dy.mebius.sample.sc.common.response.ResResult;
import dy.mebius.sample.sc.order.feign.HelloServiceFeignApi;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFeignApiFallback implements HelloServiceFeignApi {
    @Override
    public ResResult<String> hello() {
        return ResResult.succeed("[HelloServiceFeignApi.hello() 开启了熔断]");
    }

    @Override
    public ResResult<String> helloForError() throws Exception {
        return ResResult.succeed("[HelloServiceFeignApi.helloForError() 开启了熔断]");
    }
}
