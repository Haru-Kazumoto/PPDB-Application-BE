package dev.pack.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Year;


@RequiredArgsConstructor
@Component
public class StudentUtils {
    public String generateIdStudent(Object lastInsertedCount, String batchCode){
        Year year = Year.now();

        return String.format("%s-%s-%s",year,batchCode,lastInsertedCount);
    }
}
