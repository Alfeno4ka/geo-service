package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

import static ru.netology.entity.Country.GERMANY;
import static ru.netology.entity.Country.RUSSIA;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocalizationServiceImplTest {

    private LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

    @MethodSource
    @ParameterizedTest
    public void testLocale(Country country, String expected) {
        String message = localizationService.locale(country);
        Assertions.assertEquals(message, expected);
    }

    public Stream<Arguments> testLocale() {
        return Stream.of(
                Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(GERMANY, "Welcome")
        );
    }
}
