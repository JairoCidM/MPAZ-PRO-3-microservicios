package cl.duoc.mpaz.resultados.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

// Tabla "intentos": cada vez que un estudiante entrega la prueba final.
// Un intento tiene varias respuestas (relación uno a muchos).
@Entity
@Table(name = "intentos")
public class Intento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El RUT del estudiante es obligatorio")
    private String rutEstudiante;

    @NotNull(message = "El objetivo es obligatorio")
    private Long objetivoId;

    private Integer correctas = 0;

    private Integer total = 0;

    private Long fecha;

    @Valid
    @NotEmpty(message = "El intento debe tener respuestas")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "intento_id")
    private List<Respuesta> respuestas = new ArrayList<>();

    public Intento() {
    }

    public Intento(String rutEstudiante, Long objetivoId, Long fecha, List<Respuesta> respuestas) {
        this.rutEstudiante = rutEstudiante;
        this.objetivoId = objetivoId;
        this.fecha = fecha;
        this.respuestas = new ArrayList<>(respuestas);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRutEstudiante() { return rutEstudiante; }
    public void setRutEstudiante(String rutEstudiante) { this.rutEstudiante = rutEstudiante; }
    public Long getObjetivoId() { return objetivoId; }
    public void setObjetivoId(Long objetivoId) { this.objetivoId = objetivoId; }
    public Integer getCorrectas() { return correctas; }
    public void setCorrectas(Integer correctas) { this.correctas = correctas; }
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    public Long getFecha() { return fecha; }
    public void setFecha(Long fecha) { this.fecha = fecha; }
    public List<Respuesta> getRespuestas() { return respuestas; }
    public void setRespuestas(List<Respuesta> respuestas) { this.respuestas = respuestas; }
}
