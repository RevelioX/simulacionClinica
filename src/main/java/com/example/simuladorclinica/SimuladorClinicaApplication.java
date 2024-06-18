package com.example.simuladorclinica;

import com.example.simuladorclinica.logic.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimuladorClinicaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SimuladorClinicaApplication.class, args);
  }

//  Controller controller = new Controller();
//       controller.prepararSimulacion(10,0,0.01,7,2,
//               2,6,7,9,4,5);
//       controller.simular();

}
