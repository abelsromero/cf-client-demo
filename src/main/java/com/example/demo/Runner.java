package com.example.demo;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v3.organizations.ListOrganizationsRequest;
import org.springframework.beans.factory.InitializingBean;

//@Component
class Runner implements InitializingBean {

    private final CloudFoundryClient client;

    public Runner(CloudFoundryClient cloudFoundryClient) {
        client = cloudFoundryClient;
    }

    @Override
    public void afterPropertiesSet() {

        var request = ListOrganizationsRequest.builder()
                .build();

        client.organizationsV3()
                .list(request)
                .block()
//                .getResources()
//                .forEach(resource -> System.out.println(resource.getName()))
        ;

        System.out.println("Running Cloud Foundry API");

    }
}
