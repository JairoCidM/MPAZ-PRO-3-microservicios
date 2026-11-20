package cl.duoc.mpaz.contenidos.exception;

// Se lanza cuando se busca un registro que no existe (responde 404)
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
