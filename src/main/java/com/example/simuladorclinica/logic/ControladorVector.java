package com.example.simuladorclinica.logic;


import com.example.simuladorclinica.VectorEstado;
import com.example.simuladorclinica.clases.SimulacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorVector {

  @Autowired
  private com.example.simuladorclinica.logic.Controller controladorSimulacion;

  @PostMapping("/simulacion")
  public List<VectorEstado> prepararSimulacion(@RequestBody SimulacionDTO request) {
    controladorSimulacion.prepararSimulacion(
            request.getLineasSimular(),
            request.getDesdeDondeMostrar(),
            request.getMediaLlegadaGeneral(),
            request.getMediaLlegadaEmergencia(),
            request.getMediaLlegadaEspecialista(),
            request.getMediaLlegadaTerapia(),
            request.getMediaAtencionGeneral(),
            request.getMediaAtencionEmergencia(),
            request.getMediaAtencionEspecialidad(),
            request.getMediaAtencionTerapia(),
            request.getMediaAtencionRecepcion()
    );
    controladorSimulacion.simular();

    return controladorSimulacion.getVectorEstadoAcumulado();


  }


}
