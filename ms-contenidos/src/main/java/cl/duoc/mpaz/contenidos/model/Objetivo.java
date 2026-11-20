package cl.duoc.mpaz.contenidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// Tabla "objetivos": un objetivo de aprendizaje por asignatura
@Entity
@Table(name = "objetivos")
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La asignatura es obligatoria")
    private String asignatura;

    @NotBlank(message = "La unidad es obligatoria")
    private String unidad;

    private String codigo;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(length = 500)
    private String descripcion;

    // Revisión UTP: PENDIENTE, APROBADO u OBSERVADO
    @Pattern(regexp = "PENDIENTE|APROBADO|OBSERVADO", message = "Estado no válido")
    private String estado = "PENDIENTE";

    @Column(length = 500)
    private String observacion = "";

    private Boolean asignado = false;

    @Min(value = 1, message = "Mínimo 1 intento")
    @Max(value = 5, message = "Máximo 5 intentos")
    private Integer maxIntentos = 2;

    private Boolean informeValidado = false;

    private Long fechaValidacion = 0L;

    public Objetivo() {
    }

    public Objetivo(String asignatura, String unidad, String codigo, String descripcion, String estado, Boolean asignado) {
        this.asignatura = asignatura;
        this.unidad = unidad;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.asignado = asignado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
    public Boolean getAsignado() { return asignado; }
    public void setAsignado(Boolean asignado) { this.asignado = asignado; }
    public Integer getMaxIntentos() { return maxIntentos; }
    public void setMaxIntentos(Integer maxIntentos) { this.maxIntentos = maxIntentos; }
    public Boolean getInformeValidado() { return informeValidado; }
    public void setInformeValidado(Boolean informeValidado) { this.informeValidado = informeValidado; }
    public Long getFechaValidacion() { return fechaValidacion; }
    public void setFechaValidacion(Long fechaValidacion) { this.fechaValidacion = fechaValidacion; }
}
