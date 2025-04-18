package main.java.datos;

import main.java.entidades.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepositorio extends JpaRepository<CuentaBancaria, Long> {
}