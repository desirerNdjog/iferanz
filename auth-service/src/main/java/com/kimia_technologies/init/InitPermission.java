package com.kimia_technologies.init;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitPermission implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
