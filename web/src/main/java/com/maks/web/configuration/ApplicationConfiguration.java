package com.maks.web.configuration;

import com.maks.repository.configuration.HibernateConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.maks.repository", "com.maks.service", "com.maks.web"})
@Import({WebConfiguration.class, SpringSecurityConfiguration.class, HibernateConfiguration.class})
public class ApplicationConfiguration {

}
