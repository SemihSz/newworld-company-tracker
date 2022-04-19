package com.newworldcompanytracker.service.token;

import com.newworldcompanytracker.utils.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

/**
 * Created by Semih, 18.04.2022
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenGeneratorService  implements Supplier<String> {

    private final TokenGenerator tokenGenerator;

    private final static int LENGTH = 16;

    @Override
    public String get() {
        return tokenGenerator.generateRandomString(LENGTH);
    }
}
