package main.java.modelos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para realizar una transferencia entre cuentas")
public class MovimientoDineroDTO {

    @Schema(description = "ID de la cuenta de origen", example = "1")
    private Long cuentaOrigenId;

    @Schema(description = "ID de la cuenta de destino", example = "2")
    private Long cuentaDestinoId;

    @Schema(description = "Monto a transferir", example = "100.50")
    private Double monto;

    public MovimientoDineroDTO() {
    }

    public MovimientoDineroDTO(Long cuentaOrigenId, Long cuentaDestinoId, Double monto) {
        this.cuentaOrigenId = cuentaOrigenId;
        this.cuentaDestinoId = cuentaDestinoId;
        this.monto = monto;
    }

    // Getters y setters
    public Long getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Long cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public Long getCuentaDestinoId() {
        return cuentaDestinoId;
    }

    public void setCuentaDestinoId(Long cuentaDestinoId) {
        this.cuentaDestinoId = cuentaDestinoId;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}