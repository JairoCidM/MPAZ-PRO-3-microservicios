package cl.duoc.mpaz.resultados.controller;

import cl.duoc.mpaz.resultados.model.Intento;
import cl.duoc.mpaz.resultados.service.IntentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Endpoints: /api/intentos
@RestController
@RequestMapping("/api/intentos")
public class IntentoController {

    private final IntentoService service;

    public IntentoController(IntentoService service) {
        this.service = service;
    }

    // GET /api/intentos?objetivoId=1&rut=25418732-1 (ambos filtros son opcionales)
    @GetMapping
    public List<Intento> listar(@RequestParam(required = false) Long objetivoId,
                                @RequestParam(required = false) String rut) {
        return service.listar(objetivoId, rut);
    }

    @GetMapping("/{id}")
    public Intento buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Intento> registrar(@Valid @RequestBody Intento intento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(intento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
