import com.example.models.Citas;
import com.example.repositories.CitasRepository;
import com.example.service.Impl.CitasServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CitasServiceImplTest {
    @Mock
    private CitasRepository citasRepository;

    @InjectMocks
    private CitasServiceImpl citasService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        Citas cita = new Citas();
        List<Citas> citas = List.of(cita);
        when(citasRepository.findAllEnable()).thenReturn(citas);

        List<Citas> result = citasService.getAll();

        assertNotNull(result);
        assertEquals(citas, result);
    }

    @Test
    public void testGetAllByUser() {
        Long userId = 1L;
        Citas cita = new Citas();
        List<Citas> citas = List.of(cita);
        when(citasRepository.findByUsuario(userId)).thenReturn(citas);

        List<Citas> result = citasService.getAllByUser(userId);

        assertNotNull(result);
        assertEquals(citas, result);
    }

    @Test
    public void testGetfecha() {
        String fecha = "2023-10-10";
        Citas cita = new Citas();
        List<Citas> citas = List.of(cita);
        when(citasRepository.findAllByFecha(fecha)).thenReturn(citas);

        List<Citas> result = citasService.getfecha(fecha);

        assertNotNull(result);
        assertEquals(citas, result);
    }

    @Test
    public void testGet() {
        Long id = 1L;
        Citas cita = new Citas();
        when(citasRepository.findById(id)).thenReturn(Optional.of(cita));

        Citas result = citasService.get(id);

        assertNotNull(result);
        assertEquals(cita, result);
    }

    @Test
    public void testCreate() {
        Citas cita = new Citas();
        when(citasRepository.save(cita)).thenReturn(cita);

        Citas result = citasService.create(cita);

        assertNotNull(result);
        assertTrue(result.getEstado());
        assertEquals(cita, result);
    }

    @Test
    public void testUpdate() {
        Citas cita = new Citas();
        cita.setId(1L);
        when(citasRepository.findById(cita.getId())).thenReturn(Optional.of(cita));
        when(citasRepository.save(any(Citas.class))).thenReturn(cita);

        Citas result = citasService.update(cita);

        assertNotNull(result);
        assertEquals(cita, result);
    }

    @Test
    public void testCancelar() {
        Long id = 1L;
        Citas cita = new Citas();
        when(citasRepository.findById(id)).thenReturn(Optional.of(cita));

        citasService.Cancelar(id);

        verify(citasRepository, times(1)).save(cita);
        assertFalse(cita.getEstado());
    }
}
