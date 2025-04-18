package main.java.negocio;

import main.java.entidades.CuentaBancaria;
import main.java.datos.BancoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GestorOperacionesImpl implements ServicioOperaciones {

    private final BancoRepositorio bancoRepositorio;

    @Autowired
    public GestorOperacionesImpl(BancoRepositorio bancoRepositorio) {
        this.bancoRepositorio = bancoRepositorio;
    }

    @Override
    @Transactional
    public boolean transferir(Long cuentaOrigenId, Long cuentaDestinoId, Double monto) {
        if (monto <= 0) {
            return false;
        }

        Optional<CuentaBancaria> cuentaOrigenOpt = bancoRepositorio.findById(cuentaOrigenId);
        Optional<CuentaBancaria> cuentaDestinoOpt = bancoRepositorio.findById(cuentaDestinoId);

        if (cuentaOrigenOpt.isEmpty() || cuentaDestinoOpt.isEmpty()) {
            return false;
        }

        CuentaBancaria cuentaOrigen = cuentaOrigenOpt.get();
        CuentaBancaria cuentaDestino = cuentaDestinoOpt.get();

        if (cuentaOrigen.getSaldo() < monto) {
            return false;
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        bancoRepositorio.save(cuentaOrigen);
        bancoRepositorio.save(cuentaDestino);

        return true;
    }
}