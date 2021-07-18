package com.td.todak.config.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptor.password:}")
    private String password;

    private final static String OUTPUT_TYPE = "base64";
    private final static String ALGORITHM = "PBEWithSHA1AndDESede";
    private final static String SALT_GEN_CLASS_NAME = "org.jasypt.salt.RandomSaltGenerator";

    @Bean("jasyptStringEncryptor")
    public PooledPBEStringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor enc = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPoolSize(1);
        config.setPassword(password);
        config.setStringOutputType(OUTPUT_TYPE);
        config.setKeyObtentionIterations(1_000);
        config.setProvider(new BouncyCastleProvider());
        config.setAlgorithm(ALGORITHM);
        config.setSaltGeneratorClassName(SALT_GEN_CLASS_NAME);

        enc.setConfig(config);

        return enc;
    }

}
