import org.springframework.boot.SpringApplication;//Spring Boot 应用的核心启动类，负责启动 Spring 应用上下文。
import org.springframework.boot.autoconfigure.SpringBootApplication;//一个组合注解，包含了@Configuration、@EnableAutoConfiguration和@ComponentScan，用于启用自动配置和组件扫描。

@SpringBootApplication //SpringBoot应用主类
public class JvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvmApplication.class, args);//指定了主配置类并传递了命令行参数
    }

}
