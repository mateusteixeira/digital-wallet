package br.com.dwallet.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.util.Objects;

public class DTOTestHelper<T> {

    public static <T> T getJsonFromFile(String path, Class<T> returnType, Class<?> classToPath) throws IOException {
        return new ObjectMapper().readValue(readFile(path, classToPath), returnType);
    }

    private static byte[] readFile(String filePath, Class<?> clazz) throws IOException {
        return ByteStreams.toByteArray(Objects.requireNonNull(clazz.getResourceAsStream(filePath)));
    }

}
