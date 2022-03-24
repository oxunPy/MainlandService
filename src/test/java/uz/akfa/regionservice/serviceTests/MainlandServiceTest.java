
package uz.akfa.regionservice.serviceTests;

import org.assertj.core.api.Assertions;

import static org.hamcrest.MatcherAssert.assertThat;

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
import uz.akfa.regionservice.models.Mainland;
import uz.akfa.regionservice.models.Region;
import uz.akfa.regionservice.repos.CountryRepository;
import uz.akfa.regionservice.repos.MainlandRepository;
import uz.akfa.regionservice.repos.RegionRepository;
import uz.akfa.regionservice.service.CountryService;
import uz.akfa.regionservice.service.MainlandService;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class MainlandServiceTest {

    @MockBean
    CountryRepository countryRepository;

    @MockBean
    MainlandRepository mainlandRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private MainlandService mainlandService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("should find all mainlands")
    void shouldFindAllMainlands() {
        when(mainlandRepository.findAll()).thenReturn(Stream.of(
                new Mainland("North A"),
                new Mainland("South A"),
                new Mainland("Europe")).collect(Collectors.toList()));
        assertEquals(3, mainlandService.findAll().size());
    }

    @Test
    @DisplayName("should save new mainland")
    void shouldSaveNewMainlandTest() {
        Mainland mainland = new Mainland("Europe");
        when(mainlandRepository.save(mainland)).thenReturn(mainland);
        assertEquals(mainland, mainlandRepository.save(mainland));
    }

    @Test
    @DisplayName("should get mainland by its id")
    void shouldGetMainlandByIdTest() {
        long id = 2;
        Mainland mainland = new Mainland("Europe");
        when(mainlandService.existsById(id)).thenReturn(true);
        when(mainlandRepository.findById(id)).thenReturn(Optional.of(mainland));
        assertEquals(mainland, mainlandService.getById(id));
    }

    @Test
    @DisplayName("should check existence of mainland by its id")
    void checkExistenceByIdTest() {
        long id = 23;
        when(countryRepository.existsById(id)).thenReturn(false);
        assertFalse(countryService.existsById(id));
    }

    @Test
    @DisplayName("should delete mainland")
    void shouldDeleteMainlandTest() {
        Mainland mainland = new Mainland(3L,"Europe");
        mainlandRepository.delete(mainland);
        when(mainlandRepository.existsById(mainland.getId())).thenReturn(true);
        when(mainlandRepository.findById(mainland.getId())).thenReturn(Optional.of(mainland));
        verify(mainlandRepository, times(1)).delete(mainland);
    }

    @Test
    @DisplayName("should assign new country")
    void shouldAssignNewCountry() {
        long country_id = 2, mainland_id = 4;
        when(countryRepository.existsById(country_id)).thenReturn(true);
        when(mainlandRepository.existsById(mainland_id)).thenReturn(true);

        Assertions.assertThat(countryService.existsById(country_id)).isEqualTo(true);
        Assertions.assertThat(mainlandRepository.existsById(mainland_id)).isEqualTo(true);
    }

    @Test
    @DisplayName("should delete an assigned region")
    void deleteCountry() {
        long country_id = 2, mainland_id = 4;

        when(countryRepository.existsById(country_id)).thenReturn(true);
        when(mainlandRepository.existsById(mainland_id)).thenReturn(true);

        Assertions.assertThat(countryService.existsById(country_id)).isEqualTo(true);
        Assertions.assertThat(mainlandService.existsById(mainland_id)).isEqualTo(true);

        when(countryRepository.getById(country_id)).thenReturn(new Country("Uzbekistan", new Mainland("Asia")));
        assertEquals(countryRepository.getById(country_id).getMainland() != null, true);
    }

}