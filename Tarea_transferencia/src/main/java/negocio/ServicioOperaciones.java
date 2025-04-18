package main.java.negocio;

public interface ServicioOperaciones {
    boolean transferir(Long cuentaOrigenId, Long cuentaDestinoId, Double monto);
}