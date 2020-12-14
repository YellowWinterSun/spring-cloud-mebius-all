package dy.mebius.sample.sc.order.controller;

import dy.mebius.sample.sc.common.response.ResResult;
import dy.mebius.sample.sc.order.feign.HelloServiceFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    /** feign api */
    @Autowired
    private HelloServiceFeignApi helloServiceFeignApi;

    @RequestMapping("/order/sayHello")
    public ResResult<String> sayHello() {
        ResResult<String> apiResult = helloServiceFeignApi.hello();
        String result = "order收到来自 HELLO-SERVICE 的回应:\n" + apiResult.getResult();
        return ResResult.succeed(result);
    }
}
