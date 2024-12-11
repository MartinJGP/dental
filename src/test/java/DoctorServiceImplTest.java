


import com.example.models.Doctor;
import com.example.repositories.DoctorRepository;
import com.example.service.Impl.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DoctorServiceImplTest {
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        Doctor doctor = new Doctor();
        List<Doctor> doctors = List.of(doctor);
        when(doctorRepository.findAllEnable()).thenReturn(doctors);

        List<Doctor> result = doctorService.getAll();

        assertNotNull(result);
        assertEquals(doctors, result);
    }

    @Test
    public void testGet() {
        Long id = 1L;
        Doctor doctor = new Doctor();
        when(doctorRepository.findByIdEnable(id)).thenReturn(Optional.of(doctor));

        Doctor result = doctorService.get(id);

        assertNotNull(result);
        assertEquals(doctor, result);
    }

    @Test
    public void testCreate() {
        Doctor doctor = new Doctor();
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        Doctor result = doctorService.create(doctor);

        assertNotNull(result);
        assertEquals(doctor, result);
    }

    @Test
    public void testUpdate() {
        Doctor doctor = new Doctor();
        doctor.setId(1L);
        when(doctorRepository.findById(doctor.getId())).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        Doctor result = doctorService.update(doctor);

        assertNotNull(result);
        assertEquals(doctor, result);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        Doctor doctor = new Doctor();
        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

        doctorService.delete(id);

        verify(doctorRepository, times(1)).save(doctor);
        assertFalse(doctor.getEstado());
    }
}