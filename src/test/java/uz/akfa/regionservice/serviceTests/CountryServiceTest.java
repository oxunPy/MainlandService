package uz.akfa.regionservice.serviceTests;

import org.assertj.core.api.Assertions;
import static org.hamcrest.MatcherAssert.assertThat;

import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.Region;
import uz.akfa.regionservice.repos.CountryRepository;
import uz.akfa.regionservice.repos.RegionRepository;
import uz.akfa.regionservice.service.CountryService;
import uz.akfa.regionservice.service.RegionService;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class CountryServiceTest {

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    RegionRepository regionRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("should find all countries")
    void shouldFindAllCountriesTest() {
        when(countryRepository.findAll()).thenReturn(Stream.of(
                new Country("England"),
                new Country("France"),
                new Country("Germany")).collect(Collectors.toList()));
        assertEquals(3, countryService.findAll().size());
    }

    @Test
    @DisplayName("should save new country")
    void shouldSaveNewCountryTest() {
        Country country = new Country("Russia");
        when(countryRepository.save(country)).thenReturn(country);
        assertEquals(country, countryService.save(country));
    }

    @Test
    @DisplayName("should get country by its id")
    void shouldGetCountryByIdTest() {
        long id = 2;
        Country country = new Country("Russia");
        when(countryRepository.findById(id)).thenReturn(Optional.of(country));
        when(countryRepository.existsById(id)).thenReturn(true);
        assertEquals(country, countryService.getById(id));
    }

    @Test
    @DisplayName("should check existence of country by its id")
    void checkExistenceByIdTest() {
        long id = 23;
        when(countryRepository.existsById(id)).thenReturn(false);
        assertFalse(countryService.existsById(id));
    }

    @Test
    @DisplayName("should delete country")
    void shouldDeleteCountryTest() {
        Country country = new Country("Russia");
        countryService.delete(country);
        verify(countryRepository, times(1)).delete(country);
    }

    @Test
    @DisplayName("should assign new region")
    void shouldAssignNewRegion() {
        long country_id = 2, region_id = 4;
        when(countryRepository.existsById(country_id)).thenReturn(true);
        when(regionRepository.existsById(region_id)).thenReturn(true);

        Assertions.assertThat(countryService.existsById(country_id)).isEqualTo(true);
        Assertions.assertThat(regionService.existsById(region_id)).isEqualTo(true);
    }

    @Test
    @DisplayName("should delete an assigned region")
    void deleteRegion() {
        long country_id = 2, region_id = 4;

        when(countryRepository.existsById(country_id)).thenReturn(true);
        when(regionRepository.existsById(region_id)).thenReturn(true);

        Assertions.assertThat(countryService.existsById(country_id)).isEqualTo(true);
        Assertions.assertThat(regionService.existsById(region_id)).isEqualTo(true);

        when(regionRepository.getById(region_id)).thenReturn(new Region("Bukhara", new Country("Uzbekistan")));
        assertFalse(regionRepository.getById(region_id) == null);
    }

}