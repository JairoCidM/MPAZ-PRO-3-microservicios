package cl.duoc.mpaz.contenidos.controller;

import cl.duoc.mpaz.contenidos.model.Pregunta;
import cl.duoc.mpaz.contenidos.service.PreguntaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Endpoints: /api/preguntas (CRUD completo)
@RestController
@RequestMapping("/api/preguntas")
public class PreguntaController {

    private final PreguntaService service;

    public PreguntaController(PreguntaService service) {
        this.service = service;
    }

    // GET /api/preguntas o /api/preguntas?objetivoId=1
    @GetMapping
    public List<Pregunta> listar(@RequestParam(required = false) Long objetivoId) {
        return service.listar(objetivoId);
    }

    @GetMapping("/{id}")
    public Pregunta buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Pregunta> crear(@Valid @RequestBody Pregunta pregunta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(pregunta));
    }

    @PutMapping("/{id}")
    public Pregunta actualizar(@PathVariable Long id, @Valid @RequestBody Pregunta pregunta) {
        return service.actualizar(id, pregunta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
