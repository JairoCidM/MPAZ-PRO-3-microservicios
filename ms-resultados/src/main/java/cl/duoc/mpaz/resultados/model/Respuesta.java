package cl.duoc.mpaz.resultados.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

// Tabla "respuestas": lo que respondió el estudiante en cada pregunta de un intento
@Entity
@Table(name = "respuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La pregunta es obligatoria")
    private Long preguntaId;

    // 0 = A, 1 = B, 2 = C
    @NotNull(message = "La alternativa elegida es obligatoria")
    private Integer elegida;

    private Boolean esCorrecta = false;

    public Respuesta() {
    }

    public Respuesta(Long preguntaId, Integer elegida, Boolean esCorrecta) {
        this.preguntaId = preguntaId;
        this.elegida = elegida;
        this.esCorrecta = esCorrecta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPreguntaId() { return preguntaId; }
    public void setPreguntaId(Long preguntaId) { this.preguntaId = preguntaId; }
    public Integer getElegida() { return elegida; }
    public void setElegida(Integer elegida) { this.elegida = elegida; }
    public Boolean getEsCorrecta() { return esCorrecta; }
    public void setEsCorrecta(Boolean esCorrecta) { this.esCorrecta = esCorrecta; }
}
