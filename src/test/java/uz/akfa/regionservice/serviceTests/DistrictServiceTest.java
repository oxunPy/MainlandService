package uz.akfa.regionservice.serviceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uz.akfa.regionservice.models.District;
import uz.akfa.regionservice.repos.DistrictRepository;
import uz.akfa.regionservice.service.DistrictService;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DistrictServiceTest {

    @MockBean
    private DistrictRepository districtRepository;

    @Autowired
    private DistrictService districtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should find all districts")
    void shouldFindAll() {
        when(districtRepository.findAll()).thenReturn(Stream.of(
                new District("Brooklyn"),
                new District("Manhettan"),
                new District("Birgmingham"),
                new District("Vabkent")
        ).collect(Collectors.toList()));
        assertEquals(4, districtService.findAll().size());
    }

    @Test
    @DisplayName("should save new district")
    void save() {
        District district = new District("Somewhere");
        when(districtRepository.save(district)).thenReturn(district);
        assertEquals(district, districtService.save(district));
    }

    @Test
    @DisplayName("should get district by its id")
    void getById() {
        long district_id = 12;
        when(districtRepository.findById(district_id)).
                thenReturn(Optional.of(new District("Sidney")));
        when(districtRepository.existsById(district_id)).thenReturn(true);
        assertEquals(new District("Sidney"), districtService.getById(district_id));
    }

    @Test
    @DisplayName("should check existence of district by its id")
    void existsById() {
        long district_id = 12;
        when(districtRepository.existsById(district_id)).thenReturn(true);
        assertTrue(districtService.existsById(district_id));
    }

    @Test
    @DisplayName("should delete district by its id")
    void delete() {
        long district_id = 12;
        District district = new District(district_id, "Somewhere");
        when(districtRepository.findById(district_id)).thenReturn(Optional.of(district));
        when(districtRepository.existsById(district_id)).thenReturn(true);
        districtService.delete(district.getId());
        verify(districtRepository, times(1)).delete(district);
    }
}