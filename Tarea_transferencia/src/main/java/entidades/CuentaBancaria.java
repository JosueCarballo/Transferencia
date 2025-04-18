package main.java.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "cuenta_bancaria")
@Schema(description = "Entidad que representa una cuenta bancaria")
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la cuenta", example = "1")
    private Long id;

    @Schema(description = "Nombre del propietario de la cuenta", example = "Juan Pérez")
    private String propietario;

    @Schema(description = "Saldo actual de la cuenta", example = "1000.50")
    private Double saldo;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String propietario, Double saldo) {
        this.propietario = propietario;
        this.saldo = saldo;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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