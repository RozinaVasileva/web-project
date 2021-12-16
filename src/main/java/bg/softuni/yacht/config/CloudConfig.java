package bg.softuni.yacht.config;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudConfig {

    // cloud-name: dmknnra7k
    //  api-key: 144334876895965
    //  api-secret: "${CLOUDINARY_SECRET}"

    @Value("${cloudinary.cloud-name}")
    private String cloudName;
    @Value("${cloudinary.api-key}")
    private String apiKey;
    @Value("gNHB7ml5oSAZl5JGwUtNTrsbhxI")
    private String apiSecret;

   @Bean
    public Cloudinary createCloudinaryConfig() {
       Map<String, Object> config = new HashMap<>();
       config.put("cloud_name", cloudName);
       config.put("api_key", apiKey);
       config.put("api_secret", apiSecret);
       return new Cloudinary(config);
   }
}
