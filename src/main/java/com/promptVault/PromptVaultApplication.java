package com.promptVault;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

@SpringBootApplication
public class PromptVaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromptVaultApplication.class, args);

    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    // @Bean
    // public SpringResourceTemplateResolver templateResolver() {
    // // SpringResourceTemplateResolver automatically integrates with Spring's own
    // // resource resolution infrastructure, which is highly recommended.
    // SpringResourceTemplateResolver templateResolver = new
    // SpringResourceTemplateResolver();
    // templateResolver.setApplicationContext(this.applicationContext);
    // templateResolver.setPrefix("/WEB-INF/templates/");
    // templateResolver.setSuffix(".html");
    // // HTML is the default value, added here for the sake of clarity.
    // templateResolver.setTemplateMode(TemplateMode.HTML);
    // // Template cache is true by default. Set to false if you want
    // // templates to be automatically updated when modified.
    // templateResolver.setCacheable(true);
    // return templateResolver;
    // }

    // @Bean
    // public SpringTemplateEngine templateEngine() {
    // // SpringTemplateEngine automatically applies SpringStandardDialect and
    // // enables Spring's own MessageSource message resolution mechanisms.
    // SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    // templateEngine.setTemplateResolver(templateResolver());
    // // Enabling the SpringEL compiler can speed up execution in most
    // // scenarios, but might be incompatible with specific cases when
    // // expressions in one template are reused across different data
    // // types, so this flag is "false" by default for safer backwards
    // // compatibility.
    // templateEngine.setEnableSpringELCompiler(true);
    // return templateEngine;
    // }

    // @Bean
    // public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    // return args -> {
    // System.out.println("Let's inspect the beans provided by Spring Boot:");
    // String[] beanNames = ctx.getBeanDefinitionNames();
    // Arrays.sort(beanNames);
    // for (String beanName : beanNames) {
    // System.out.println(beanName);
    // }

    // };
    // }

}