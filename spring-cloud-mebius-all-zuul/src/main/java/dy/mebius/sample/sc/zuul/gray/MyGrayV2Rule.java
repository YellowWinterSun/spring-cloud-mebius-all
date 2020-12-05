package dy.mebius.sample.sc.zuul.gray;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MyGrayV2Rule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    private Server choose(ILoadBalancer lb, Object key) {
        System.out.println("in MyGrayV2Rule");
        if (lb == null) {
            return null;
        }

        List<Server> serverList = lb.getReachableServers();
        if (CollectionUtils.isEmpty(serverList)) {
            return null;
        }

        // todo 此处 过滤灰度版本号
        for (Server server : serverList) {
            if (server instanceof DiscoveryEnabledServer) {
                DiscoveryEnabledServer discoveryEnabledServer = (DiscoveryEnabledServer) server;
                Map<String, String> metadataMap = discoveryEnabledServer.getInstanceInfo().getMetadata();
                String instanceVersion = metadataMap.get("instanceVersion");
                System.out.println("server:" + server.getHostPort() + " metadata-version:" + instanceVersion);
            }
        }

        return serverList.get(ThreadLocalRandom.current().nextInt(serverList.size()));
    }
}
