package main.java.modelos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para crear una nueva cuenta bancaria")
public class NuevaCuentaDTO {

    @Schema(description = "Nombre del propietario de la cuenta", example = "Ana Martínez")
    private String propietario;

    @Schema(description = "Saldo inicial de la cuenta", example = "1500.00")
    private Double saldo;

    public NuevaCuentaDTO() {
    }

    public NuevaCuentaDTO(String propietario, Double saldo) {
        this.propietario = propietario;
        this.saldo = saldo;
    }

    // Getters y setters
    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}