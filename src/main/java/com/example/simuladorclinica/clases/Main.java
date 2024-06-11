package com.example.simuladorclinica.clases;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de TipoAtencion
        TipoAtencion consultaMedica = new TipoAtencion("Consulta Médica");
        TipoAtencion emergencia = new TipoAtencion("Emergencia");
        TipoAtencion consultaEspecialista = new TipoAtencion("Consulta con Especialista");
        TipoAtencion terapeuta = new TipoAtencion("Terapeuta");

        // Mostrar los nombres de los tipos de atención
        System.out.println("Tipos de Atención:");
        System.out.println(consultaMedica);
        System.out.println(emergencia);
        System.out.println(consultaEspecialista);
        System.out.println(terapeuta);
    }
}

