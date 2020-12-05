package glsocialnetwork;

import glsocialnetwork.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class GlSocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlSocialNetworkApplication.class, args);
	}

}
