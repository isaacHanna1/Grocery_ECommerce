package com.watad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class WebConfig {

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // Set maximum upload size (10MB)
        resolver.setMaxUploadSize(10 * 1024 * 1024);
        // Set maximum size per file (5MB)
        resolver.setMaxUploadSizePerFile(5 * 1024 * 1024);
        // Set memory threshold (1MB - files larger than this will be written to disk)
        resolver.setMaxInMemorySize(1 * 1024 * 1024);
        return resolver;
    }

}
