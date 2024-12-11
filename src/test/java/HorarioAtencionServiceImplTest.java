import com.example.models.HorarioAtencion;
import com.example.repositories.HorarioAtencionRepository;
import com.example.service.Impl.HorarioAtencionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HorarioAtencionServiceImplTest {
    @Mock
    private HorarioAtencionRepository horarioAtencionRepository;

    @InjectMocks
    private HorarioAtencionServiceImpl horarioAtencionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        HorarioAtencion horario = new HorarioAtencion();
        List<HorarioAtencion> horarios = List.of(horario);
        when(horarioAtencionRepository.findAll()).thenReturn(horarios);

        List<HorarioAtencion> result = horarioAtencionService.getAll();

        assertNotNull(result);
        assertEquals(horarios, result);
    }

    @Test
    public void testGet() {
        Long id = 1L;
        HorarioAtencion horario = new HorarioAtencion();
        when(horarioAtencionRepository.findById(id)).thenReturn(Optional.of(horario));

        HorarioAtencion result = horarioAtencionService.get(id);

        assertNotNull(result);
        assertEquals(horario, result);
    }

    @Test
    public void testCreate() {
        HorarioAtencion horario = new HorarioAtencion();
        when(horarioAtencionRepository.save(horario)).thenReturn(horario);

        HorarioAtencion result = horarioAtencionService.create(horario);

        assertNotNull(result);
        assertTrue(result.getHabilitado());
        assertEquals(horario, result);
    }

    @Test
    public void testUpdate() {
        HorarioAtencion horario = new HorarioAtencion();
        horario.setId(1L);
        when(horarioAtencionRepository.findById(horario.getId())).thenReturn(Optional.of(horario));
        when(horarioAtencionRepository.save(any(HorarioAtencion.class))).thenReturn(horario);

        HorarioAtencion result = horarioAtencionService.update(horario);

        assertNotNull(result);
        assertEquals(horario, result);
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        HorarioAtencion horario = new HorarioAtencion();
        when(horarioAtencionRepository.findById(id)).thenReturn(Optional.of(horario));

        horarioAtencionService.delete(id);

        verify(horarioAtencionRepository, times(1)).delete(horario);
    }

    @Test
    public void testGetallFecha() {
        String fecha = "2023-10-10";
        HorarioAtencion horario = new HorarioAtencion();
        List<HorarioAtencion> horarios = List.of(horario);
        when(horarioAtencionRepository.findAllByFecha(fecha)).thenReturn(horarios);

        List<HorarioAtencion> result = horarioAtencionService.getallFecha(fecha);

        assertNotNull(result);
        assertEquals(horarios, result);
    }

    @Test
    public void testDisable() {
        Long id = 1L;
        HorarioAtencion horario = new HorarioAtencion();
        when(horarioAtencionRepository.findById(id)).thenReturn(Optional.of(horario));

        horarioAtencionService.disable(id);

        verify(horarioAtencionRepository, times(1)).save(horario);
        assertFalse(horario.getHabilitado());
    }

    @Test
    public void testEnable() {
        Long id = 1L;
        HorarioAtencion horario = new HorarioAtencion();
        when(horarioAtencionRepository.findById(id)).thenReturn(Optional.of(horario));

        horarioAtencionService.enable(id);

        verify(horarioAtencionRepository, times(1)).save(horario);
        assertTrue(horario.getHabilitado());
    }
}
