package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.service.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Log4j2
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    @Override
    public String digest(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(input.getBytes());
            byte[] bytes = messageDigest.digest();
            BigInteger number = new BigInteger(1, bytes);
            log.info("Received hashed password");
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
