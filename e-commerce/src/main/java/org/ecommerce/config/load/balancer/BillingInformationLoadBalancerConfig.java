package org.ecommerce.config.load.balancer;

import org.loadbalancer.RoundRobinLoadBalancerConfig;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@LoadBalancerClient(name = "billing-information-service", configuration = RoundRobinLoadBalancerConfig.class)
public class BillingInformationLoadBalancerConfig {
}
