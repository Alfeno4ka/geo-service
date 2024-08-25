package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GeoServiceImplTest {
    private GeoServiceImpl geoService = new GeoServiceImpl();

    @MethodSource
    @ParameterizedTest
    public void testByIp(String ip, Location expected) {
        Location location = geoService.byIp(ip);
        Assertions.assertEquals(location.getCountry(), expected.getCountry());
        Assertions.assertEquals(location.getBuiling(), expected.getBuiling());
        Assertions.assertEquals(location.getCity(), expected.getCity());
        Assertions.assertEquals(location.getStreet(), expected.getStreet());
    }

    public Stream<Arguments> testByIp() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("96.55.185.150", new Location("New York", Country.USA, null, 0)),
                Arguments.of("172.33.33.33", new Location("Moscow", Country.RUSSIA, null, 0))
        );
    }

    @Test
    public void testByIpNull() {
        Location location = geoService.byIp("33.33.33.33");
        Assertions.assertNull(location);
    }
}
