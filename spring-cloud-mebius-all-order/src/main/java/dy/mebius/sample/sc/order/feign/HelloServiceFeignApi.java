package dy.mebius.sample.sc.order.feign;

import dy.mebius.sample.sc.common.response.ResResult;
import dy.mebius.sample.sc.order.feign.fallback.HelloServiceFeignApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "hello-service", fallback = HelloServiceFeignApiFallback.class)
public interface HelloServiceFeignApi {

    @RequestMapping("/hello")
    ResResult<String> hello();

    @RequestMapping("/helloForError")
    ResResult<String> helloForError() throws Exception;
}
