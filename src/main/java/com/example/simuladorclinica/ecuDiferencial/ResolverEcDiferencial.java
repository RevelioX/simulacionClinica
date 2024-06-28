package com.example.simuladorclinica.ecuDiferencial;

public class ResolverEcDiferencial {
    public static double resolverEcuacion(String ecDiferencial, double reloj){

        RungeKutta rungekutta = new RungeKutta(0,(float) reloj,0.1f,ecDiferencial);

        double resultado = (double) rungekutta.calcularRunge();

        return resultado;
    }
}
