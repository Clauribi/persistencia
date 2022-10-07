package com.example.demo.controllers;

import com.example.demo.domain.NoExisteReservaException;
import com.example.demo.domain.Reserva;
import com.example.demo.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @PostMapping("/reservas/coche/{matricula}/cliente/{dni}")
    public ResponseEntity<String> reservasByMatriculaByDni(@Valid @PathVariable String matricula, @PathVariable String dni) {
        try {
            Reserva reserva = new Reserva(matricula, dni);
            reservaRepository.save(reserva);
        } catch (Exception e1) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/reservas/coche/{matricula}/cliente/{dni}")
    public ResponseEntity<String> cancelarReservaCoches(@Valid @PathVariable String matricula, @PathVariable String dni) {
        try {
            NoExisteReserva(matricula);
        }catch (NoExisteReservaException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception e1) {
            return ResponseEntity.badRequest().build();
        }
        Reserva reserva = new Reserva(matricula, dni);
        reservaRepository.delete(reserva);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



    private void NoExisteReserva(String matricula) throws NoExisteReservaException {
        if (!reservaRepository.existsById(matricula)) {
            throw new NoExisteReservaException();
        }
    }


}
