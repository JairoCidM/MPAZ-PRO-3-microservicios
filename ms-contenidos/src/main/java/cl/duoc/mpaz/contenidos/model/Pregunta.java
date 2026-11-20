package cl.duoc.mpaz.contenidos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

// Tabla "preguntas": se usan en los desafíos (nivel 1 a 3) y en la prueba final (nivel 0)
@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El objetivo es obligatorio")
    private Long objetivoId;

    @Pattern(regexp = "DESAFIO|PRUEBA", message = "El tipo debe ser DESAFIO o PRUEBA")
    private String tipo;

    @Min(value = 0, message = "Nivel mínimo 0")
    @Max(value = 3, message = "Nivel máximo 3")
    private Integer nivel = 0;

    @NotBlank(message = "El enunciado es obligatorio")
    private String enunciado;

    @NotBlank(message = "La alternativa A es obligatoria")
    private String opcionA;

    @NotBlank(message = "La alternativa B es obligatoria")
    private String opcionB;

    @NotBlank(message = "La alternativa C es obligatoria")
    private String opcionC;

    // 0 = A, 1 = B, 2 = C
    @NotNull(message = "Indica la respuesta correcta")
    @Min(value = 0, message = "Respuesta correcta no válida")
    @Max(value = 2, message = "Respuesta correcta no válida")
    private Integer correcta;

    public Pregunta() {
    }

    public Pregunta(Long objetivoId, String tipo, Integer nivel, String enunciado,
                    String opcionA, String opcionB, String opcionC, Integer correcta) {
        this.objetivoId = objetivoId;
        this.tipo = tipo;
        this.nivel = nivel;
        this.enunciado = enunciado;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.correcta = correcta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getObjetivoId() { return objetivoId; }
    public void setObjetivoId(Long objetivoId) { this.objetivoId = objetivoId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getNivel() { return nivel; }
    public void setNivel(Integer nivel) { this.nivel = nivel; }
    public String getEnunciado() { return enunciado; }
    public void setEnunciado(String enunciado) { this.enunciado = enunciado; }
    public String getOpcionA() { return opcionA; }
    public void setOpcionA(String opcionA) { this.opcionA = opcionA; }
    public String getOpcionB() { return opcionB; }
    public void setOpcionB(String opcionB) { this.opcionB = opcionB; }
    public String getOpcionC() { return opcionC; }
    public void setOpcionC(String opcionC) { this.opcionC = opcionC; }
    public Integer getCorrecta() { return correcta; }
    public void setCorrecta(Integer correcta) { this.correcta = correcta; }
}
