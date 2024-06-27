package com.example.simuladorclinica.ecuDiferencial;

public class ResolverEcDiferencial {
    public static double resolverEcuacion(String ecDiferencial){

        RungeKutta rungekutta = new RungeKutta(0,1,0.1f,ecDiferencial);

        double resultado = (double) rungekutta.calcularRunge();

        return resultado;
    }
}
