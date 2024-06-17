package com.example.simuladorclinica;

import com.example.simuladorclinica.logic.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class SimuladorClinicaApplication {

    public static void main(String[] args) {

       Controller controller = new Controller();
       controller.prepararSimulacion(200,20,8,7,2,
               2,6,7,9,4,5);
       controller.simular();
    }

}
