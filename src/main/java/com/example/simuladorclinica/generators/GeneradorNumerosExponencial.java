package com.example.simuladorclinica.generators;

import com.example.simuladorclinica.errors.NoMoreRandomNumbers;

import java.util.ArrayList;
import java.util.List;

public class GeneradorNumerosExponencial extends Generador {
    private double lambda;

    public GeneradorNumerosExponencial(double lambda){
        super();
        this.lambda = lambda;
    }


    public void generarValor(int cantidad) {
        double mu = lambda;
        for(int i = 0; i < cantidad; i++){
            double random = Math.random();
            double generado = - mu * Math.log(1 - random);
            valores.add(generado);
        }
    }

}
