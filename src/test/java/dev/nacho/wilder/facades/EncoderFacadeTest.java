package dev.nacho.wilder.facades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dev.nacho.wilder.facades.encryptations.Base64Encoder;

public class EncoderFacadeTest {

    private EncoderFacade facade = new EncoderFacade(new BCryptPasswordEncoder(), new Base64Encoder());

    @Test
    void testDecode() {
        assertEquals("test", facade.decode("base64", "dGVzdA=="));
    }

    @Test
    void testEncode() {
        assertEquals("dGVzdA==", facade.encode("base64", "test"));
        assertNotNull(facade.encode("bcrypt", "test"));
    }
}
