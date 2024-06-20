package com.example.simuladorclinica;

import com.example.simuladorclinica.logic.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimuladorClinicaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SimuladorClinicaApplication.class, args);
  }
//

  Controller controller = new Controller();
//    controller.prepararSimulacion(1000000,999000,18,12,15,
//            10,6,10,4,4,5);
//    controller.simular();



}
