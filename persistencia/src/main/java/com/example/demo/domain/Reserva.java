package com.example.demo.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "reservas")
public class Reserva {
    @Id
    @NotNull(message = "Matricula is null")
    @NotBlank(message = "Matricula is empty")
    private String matricula;
    @NotNull(message = "Dni is null")
    @NotBlank(message = "Dni is empty")
    private String dni;


    public Reserva( String matricula, String dni) {
        this.matricula = matricula;
        this.dni = dni;
    }

    public Reserva() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
