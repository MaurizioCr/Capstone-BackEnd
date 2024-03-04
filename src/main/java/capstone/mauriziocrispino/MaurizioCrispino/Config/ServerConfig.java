package capstone.mauriziocrispino.MaurizioCrispino.Config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfig {
    @Value("${cloudinary.name}")
    private String cloudName;

    @Value("${cloudinary.apikey}")
    private String apiKey;

    @Value("${cloudinary.secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinaryUploader() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }
}
