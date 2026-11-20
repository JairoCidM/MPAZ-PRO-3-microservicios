package cl.duoc.mpaz.contenidos.controller;

import cl.duoc.mpaz.contenidos.model.Objetivo;
import cl.duoc.mpaz.contenidos.service.ObjetivoService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Endpoints: /api/objetivos
@RestController
@RequestMapping("/api/objetivos")
public class ObjetivoController {

    private final ObjetivoService service;

    public ObjetivoController(ObjetivoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Objetivo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Objetivo buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Objetivo> crear(@Valid @RequestBody Objetivo objetivo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(objetivo));
    }

    @PutMapping("/{id}")
    public Objetivo actualizar(@PathVariable Long id, @Valid @RequestBody Objetivo objetivo) {
        return service.actualizar(id, objetivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
