package uz.akfa.regionservice.serviceTests;

import org.assertj.core.api.Assertions;
import org.checkerframework.checker.nullness.Opt;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uz.akfa.regionservice.models.Country;
import uz.akfa.regionservice.models.District;
import uz.akfa.regionservice.models.Mainland;
import uz.akfa.regionservice.models.Region;
import uz.akfa.regionservice.repos.CountryRepository;
import uz.akfa.regionservice.repos.DistrictRepository;
import uz.akfa.regionservice.repos.MainlandRepository;
import uz.akfa.regionservice.repos.RegionRepository;
import uz.akfa.regionservice.service.DistrictService;
import uz.akfa.regionservice.service.RegionService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegionServiceTest {

    @MockBean
    RegionRepository regionRepository;

    @MockBean
    DistrictRepository districtRepository;

    @Autowired
    private RegionService regionService;

    @Autowired
    private DistrictService districtService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("should find all regions")
    void shouldFindAllMainlands() {
        when(regionRepository.findAll()).thenReturn(Stream.of(
                new Region("London"),
                new Region("Los Angeles"),
                new Region("York-shire")).collect(Collectors.toList()));
        assertEquals(3, regionService.findAll().size());
    }

    @Test
    @DisplayName("should save new mainland")
    void shouldSaveNewMainlandTest() {
        Region region = new Region("Bukhara");
        when(regionRepository.save(region)).thenReturn(region);
        assertEquals(region, regionService.save(region));
    }

    @Test
    @DisplayName("should get mainland by its id")
    void shouldGetMainlandByIdTest() {
        long id = 2;
        Region region = new Region("Bukhara");
        when(regionRepository.existsById(id)).thenReturn(true);
        when(regionRepository.findById(id)).thenReturn(Optional.of(region));
        assertEquals(region, regionService.getById(id));
    }

    @Test
    @DisplayName("should check existence of mainland by its id")
    void checkExistenceByIdTest() {
        long id = 23;
        when(regionRepository.existsById(id)).thenReturn(false);
        assertFalse(regionRepository.existsById(id));
    }

    @Test
    @DisplayName("should delete mainland")
    void shouldDeleteMainlandTest() {
        Region region = new Region("Bukhara");
        region.setId(1L);
        regionRepository.delete(region);
        when(regionRepository.existsById(region.getId())).thenReturn(true);
        when(regionRepository.findById(region.getId())).thenReturn(Optional.of(region));
        verify(regionRepository, times(1)).delete(region);
    }

    @Test
    @DisplayName("should assign new district")
    void shouldAssignNewDistrict() {
        long region_id = 2, district_id = 4;
        when(regionRepository.existsById(region_id)).thenReturn(true);
        when(districtRepository.existsById(district_id)).thenReturn(true);

        Assertions.assertThat(regionService.existsById(region_id)).isEqualTo(true);
        Assertions.assertThat(districtService.existsById(district_id)).isEqualTo(true);
    }

    @Test
    @DisplayName("should delete an assigned district")
    void deleteDistrict() {
        long region_id = 2, district_id = 4;

        when(regionRepository.existsById(region_id)).thenReturn(true);
        when(districtRepository.existsById(district_id)).thenReturn(true);

        Assertions.assertThat(regionService.existsById(region_id)).isEqualTo(true);
        Assertions.assertThat(districtService.existsById(district_id)).isEqualTo(true);

        District district = new District(1L,"Vabkent");
        district.setRegion(new Region("Bukhara"));
        when(districtRepository.findById(district_id)).thenReturn(Optional.of(district));
        assertEquals(districtService.getById(district_id).getRegion() != null, true);
    }
}