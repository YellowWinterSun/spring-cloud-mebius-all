package dy.mebius.sample.sc.order.header;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class SecuringRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // SpringBoot提供RequestContextHolder，可以帮助我们在任意地方获取当前 httpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取当前请求的header数组
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);

                // requestTemplate是feign调用下游的 httpRequest，我们要把当前的httpRequest的header数据拷贝给下游的httpRequest
                requestTemplate.header(name, values);
            }
        }
    }
}
