package com.beeoverflow.apibeeoverflow.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String CLOUDINARY_URL = "cloudinary://368948467361132:rE2w5un-z7Dw_Ciz-KBhw3lyXN8@dbrftgkrp";

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(CLOUDINARY_URL);
    }
}
