package main.java.web;

import main.java.modelos.MovimientoDineroDTO;
import main.java.modelos.NuevaCuentaDTO;
import main.java.negocio.ServicioOperaciones;
import main.java.datos.BancoRepositorio;
import main.java.entidades.CuentaBancaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/operaciones")
@Tag(name = "Operaciones Bancarias", description = "API para gestionar operaciones entre cuentas")
public class ControladorBanco {

    private final ServicioOperaciones servicioOperaciones;
    private final BancoRepositorio bancoRepositorio;

    @Autowired
    public ControladorBanco(ServicioOperaciones servicioOperaciones, BancoRepositorio bancoRepositorio) {
        this.servicioOperaciones = servicioOperaciones;
        this.bancoRepositorio = bancoRepositorio;
    }

    @PostMapping
    @Operation(summary = "Realizar una transferencia entre cuentas",
            description = "Transfiere un monto desde una cuenta origen a una cuenta destino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transferencia realizada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de transferencia inválidos"),
            @ApiResponse(responseCode = "422", description = "No se pudo realizar la transferencia (saldo insuficiente o cuentas inexistentes)")
    })
    public ResponseEntity<String> realizarMovimiento(@RequestBody MovimientoDineroDTO movimientoDTO) {
        if (movimientoDTO.getCuentaOrigenId() == null ||
                movimientoDTO.getCuentaDestinoId() == null ||
                movimientoDTO.getMonto() == null ||
                movimientoDTO.getMonto() <= 0) {
            return ResponseEntity.badRequest().body("Datos de movimiento inválidos");
        }

        boolean resultado = servicioOperaciones.transferir(
                movimientoDTO.getCuentaOrigenId(),
                movimientoDTO.getCuentaDestinoId(),
                movimientoDTO.getMonto()
        );

        if (resultado) {
            return ResponseEntity.ok("Movimiento de dinero realizado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("No se pudo realizar el movimiento. Verifica que las cuentas existan y que haya saldo suficiente.");
        }
    }

    @GetMapping("/cuentas")
    @Operation(summary = "Obtener todas las cuentas",
            description = "Devuelve la lista de todas las cuentas bancarias")
    @ApiResponse(responseCode = "200", description = "Lista de cuentas recuperada con éxito")
    public List<CuentaBancaria> obtenerCuentas() {
        return bancoRepositorio.findAll();
    }

    @GetMapping("/cuentas/{id}")
    @Operation(summary = "Obtener una cuenta por ID",
            description = "Devuelve los detalles de una cuenta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta encontrada"),
            @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    })
    public ResponseEntity<CuentaBancaria> obtenerCuentaPorId(@PathVariable Long id) {
        return bancoRepositorio.findById(id)
                .map(cuenta -> ResponseEntity.ok(cuenta))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/cuentas")
    @Operation(summary = "Crear una nueva cuenta bancaria",
            description = "Crea una nueva cuenta con el propietario y saldo especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cuenta creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de cuenta inválidos")
    })
    public ResponseEntity<CuentaBancaria> crearCuenta(@RequestBody NuevaCuentaDTO nuevaCuentaDTO) {
        if (nuevaCuentaDTO.getPropietario() == null || nuevaCuentaDTO.getPropietario().trim().isEmpty() ||
                nuevaCuentaDTO.getSaldo() == null || nuevaCuentaDTO.getSaldo() < 0) {
            return ResponseEntity.badRequest().build();
        }

        CuentaBancaria nuevaCuenta = new CuentaBancaria(
                nuevaCuentaDTO.getPropietario(),
                nuevaCuentaDTO.getSaldo()
        );

        CuentaBancaria cuentaGuardada = bancoRepositorio.save(nuevaCuenta);

        return ResponseEntity.status(HttpStatus.CREATED).body(cuentaGuardada);
    }
}