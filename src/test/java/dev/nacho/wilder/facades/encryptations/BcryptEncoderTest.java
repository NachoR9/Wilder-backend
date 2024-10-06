package dev.nacho.wilder.facades.encryptations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

    private BcryptEncoder encoder = new BcryptEncoder(new BCryptPasswordEncoder());

    @Test
    void testEncode() {
        assertNotNull(encoder.encode("test"));
    }
}
