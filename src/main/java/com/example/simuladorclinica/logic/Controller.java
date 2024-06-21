package com.example.simuladorclinica.logic;

import com.example.simuladorclinica.VectorEstado;
import com.example.simuladorclinica.clases.*;
import com.example.simuladorclinica.generators.Generador;
import com.example.simuladorclinica.generators.GeneradorNumerosExponencial;
import com.example.simuladorclinica.generators.GeneradorNumerosNormales;
import com.example.simuladorclinica.generators.GeneradorNumerosUniformes;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component

public class Controller {
    private VectorEstado vectorEstado;

    private List<VectorEstado> vectorAcumulador = new ArrayList<>();
    private PriorityQueue<Evento> eventos;
    private double reloj;
    private double tiempoDesdeAnteriorEvento;
    List<Servidor> servidores = new ArrayList<>();
    private Generador generadorLlegadasGeneral;
    private Generador generadorLlegadasEmergencia;
    private Generador generadorLlegadasEspecialista;
    private Generador generadorLlegadaTerapia;
    private Generador generadorLlegadaRecepcion;
    private Generador generadorFinAtencionGeneral;
    private Generador generadorFinAtencionEmergencia;
    private Generador generadorFinAtencionEspecialista;
    private Generador generadorFinAtencionTerapia;
    private Generador generadorFinAtencionRecepcion;

    private int lineasSimular;

    private int desdeDondeMostrar;

    boolean seDebeMostrar;

    //Datos Estadisticossss
    private int contadorPacientesAtendidos;
    private double acumuladorTiempoEsperaPacientes;
    private double acumuladorTiempoEsperaPacientesEmergencia;

    public void prepararSimulacion(int lineasSimular, int desdeDondeMostrar, double mediaLlegadaGeneral, double mediaLlegadaEmergencia, double mediaLlegadaEspecialista, double mediaLlegadaTerapia, double mediaAtencionGeneral, double mediaAtencionEmergencia, double mediaAtencionEspecialidad, double mediaAtencionTerapia, double mediaAtencionRecepcion){
        this.desdeDondeMostrar = desdeDondeMostrar;
        this.lineasSimular = lineasSimular;

        prepararColaEventos();
        instanciarGeneradoresLlegadas(mediaLlegadaGeneral, mediaLlegadaEmergencia,mediaLlegadaEspecialista, mediaLlegadaTerapia);
        instanciarGeneradoresAtencion(mediaAtencionGeneral, mediaAtencionEmergencia,mediaAtencionEspecialidad,mediaAtencionTerapia, mediaAtencionRecepcion);
        instanciarServidores();
        generarPrimerosEventos();
    }


    private void prepararColaEventos(){
        Comparator<Evento> comparator = Comparator.comparing(Evento::getTiempo);
        eventos = new PriorityQueue<Evento>(comparator);
    }

    private void instanciarGeneradoresLlegadas( double mediaLlegadaGeneral, double mediaLlegadaEmergencia, double mediaLlegadaEspecialista, double mediaLlegadaTerapia){
        generadorLlegadasGeneral = new GeneradorNumerosExponencial(mediaLlegadaGeneral);
        generadorLlegadasGeneral.generarValor(1000);

        generadorLlegadasEmergencia = new GeneradorNumerosExponencial(mediaLlegadaEmergencia);
        generadorLlegadasEmergencia.generarValor(1000);

        generadorLlegadasEspecialista = new GeneradorNumerosExponencial(mediaLlegadaEspecialista);
        generadorLlegadasEspecialista.generarValor(1000);

        generadorLlegadaTerapia = new GeneradorNumerosExponencial(mediaLlegadaTerapia);
        generadorLlegadaTerapia.generarValor(1000);

        generadorLlegadaRecepcion = new GeneradorNumerosUniformes(0,1);
        generadorLlegadaRecepcion.generarValor(1000);

    }

    private void instanciarGeneradoresAtencion(double mediaAtencionGeneral, double mediaAtencionEmergencia, double mediaAtencionEspecialidad, double mediaAtencionTerapia, double mediaAtencionRecepcion){
        generadorFinAtencionGeneral = new GeneradorNumerosExponencial(mediaAtencionGeneral);
        generadorFinAtencionGeneral.generarValor(1000);

        generadorFinAtencionEmergencia = new GeneradorNumerosExponencial(mediaAtencionEmergencia);
        generadorFinAtencionEmergencia.generarValor(1000);

        generadorFinAtencionEspecialista = new GeneradorNumerosExponencial(mediaAtencionEspecialidad);
        generadorFinAtencionEspecialista.generarValor(1000);

        generadorFinAtencionTerapia = new GeneradorNumerosExponencial(mediaAtencionTerapia);
        generadorFinAtencionTerapia.generarValor(1000);

        generadorFinAtencionRecepcion = new GeneradorNumerosExponencial(mediaAtencionRecepcion);
        generadorFinAtencionRecepcion.generarValor(1000);

    }

    private void instanciarServidores(){
        Servidor general1 = new Servidor(TipoAtencion.General,1);
        Servidor general2 = new Servidor(TipoAtencion.General,2);
        Servidor general3 = new Servidor(TipoAtencion.General,3);

        servidores.add(general1);
        servidores.add(general2);
        servidores.add(general3);

        Servidor emergencias1 = new Servidor(TipoAtencion.Emergencia,1);
        Servidor emergencias2 = new Servidor(TipoAtencion.Emergencia,2);

        servidores.add(emergencias1);
        servidores.add(emergencias2);

        Servidor especialista1 = new Servidor(TipoAtencion.Especialista,1);
        Servidor especialista2 = new Servidor(TipoAtencion.Especialista,2);
        Servidor especialista3 = new Servidor(TipoAtencion.Especialista,3);
        Servidor especialista4 = new Servidor(TipoAtencion.Especialista,4);

        servidores.add(especialista1);
        servidores.add(especialista2);
        servidores.add(especialista3);
        servidores.add(especialista4);

        Servidor terapia1 = new Servidor(TipoAtencion.Terapia,1);
        Servidor terapia2 = new Servidor(TipoAtencion.Terapia,2);

        servidores.add(terapia1);
        servidores.add(terapia2);

        Servidor recepcion = new Servidor(TipoAtencion.Recepcion,1);
        servidores.add(recepcion);


    }
    private void generarPrimerosEventos(){
        double tiempo;
        tiempo = generadorLlegadasGeneral.getValor();
        Evento eventoLlegadaGeneral = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_GENERAL);

        tiempo = generadorLlegadasEmergencia.getValor();
        Evento eventoLlegadaEmergencia = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_EMERGENCIA);

        tiempo = generadorLlegadasEspecialista.getValor();
        Evento eventoLlegadaEspecialista = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_ESPECIALIDAD);

        tiempo = generadorLlegadaTerapia.getValor();
        Evento eventoLlegadaTerapia = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_TERAPIA);

        eventos.add(eventoLlegadaGeneral);
        eventos.add(eventoLlegadaEspecialista);
        eventos.add(eventoLlegadaEmergencia);
        eventos.add(eventoLlegadaTerapia);
    }
    public void simular(){
        for(int i = 0; i < lineasSimular; i++){
            if ((i > desdeDondeMostrar && i < desdeDondeMostrar + 300) || i == lineasSimular - 1){
                seDebeMostrar = true;
                vectorEstado = new VectorEstado();
            }else{
                seDebeMostrar = false;
            }
            Evento eventoAResolver = eventos.remove();
            tiempoDesdeAnteriorEvento = eventoAResolver.getTiempo() - reloj;
            reloj = eventoAResolver.getTiempo();
            resolverEvento(eventoAResolver);
            if (seDebeMostrar) {
                armarVector();
            }


        }

    }

    private void resolverEvento(Evento evento){

        //System.out.println("------------" + evento.getTipoEvento().getNombre() + "------------" + reloj );
        TipoEvento tipoEvento = evento.getTipoEvento();

        if(seDebeMostrar){
            vectorEstado.setReloj(String.valueOf(reloj));
            vectorEstado.setEvento(evento.getTipoEvento().getNombre());
        }

        if(tipoEvento.esDeLlegada()){
            resolverEventoLlegada(evento);
        } else if(tipoEvento.esDeFinAtencion()){
            resolverFinAtencion(evento);
        }//else if(tipoEvento.esDeRecepcion()){
        //   resolverFinAtencionRecepcion(evento);
        //}
    }

    private void resolverEventoLlegada(Evento evento){

        List<Servidor> serviroresTipoCorrespondiente = servidores.stream().filter(
                servidor -> {
                    boolean b = servidor.getTipoAtencion() == evento.getTipoEvento().getTipoAtencion();
                    return b;
                }
        ).collect(Collectors.toList());


        Servidor servidorCorrespondiente = serviroresTipoCorrespondiente.stream().min(
                Comparator.comparing(Servidor::getLongitud)
        ).orElse(null);
        boolean servidorVacio = servidorCorrespondiente.getLongitud() == -1;

        Paciente paciente = new Paciente(evento.getTipoEvento().getTipoAtencion());
        servidorCorrespondiente.añadirCola(paciente);

        //generar el proximo evento de llegada
        double tiempo = 0;
        if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.General){
            tiempo = generadorLlegadasGeneral.getValor();
        } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Emergencia){
            tiempo = generadorLlegadasEmergencia.getValor();
        } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Especialista){
            tiempo = generadorLlegadasEspecialista.getValor();
        } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Terapia){
            tiempo = generadorLlegadaTerapia.getValor();
        }

        double tiempoProximoEvento = tiempo + reloj;

        Evento sigEvento = new Evento(tiempoProximoEvento, evento.getTipoEvento() );
        eventos.add(sigEvento);

        //generar el evento finAtención correspondiente si el Paciente entro en un servidor con cola vacia.
        if(servidorVacio){
            tiempo = 0;
            TipoEvento tipoEventoFinAtencion = null;
            if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.General){
                tiempo = generadorFinAtencionGeneral.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_GENERAL;
            } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Emergencia){
                tiempo = generadorFinAtencionEmergencia.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_EMERGENCIA;
            } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Especialista){
                tiempo = generadorFinAtencionEspecialista.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_ESPECIALIDAD;
            } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Terapia){
                tiempo = generadorFinAtencionTerapia.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_TERAPIA;
            }
            tiempoProximoEvento = tiempo + reloj;

            sigEvento = new Evento(tiempoProximoEvento, tipoEventoFinAtencion, servidorCorrespondiente);
            eventos.add(sigEvento);
        }
        actualizarTiemposEspera();
    }

    private void resolverFinAtencion(Evento evento){
        System.out.println(contadorPacientesAtendidos);
        Servidor servidorFinalizacion = evento.getServidor();
        ;

        double randomRecepcion = generadorLlegadaRecepcion.getValor();
        boolean continuarAtencion;
        if (randomRecepcion < 0.25){
            continuarAtencion = true;
        }else{
            continuarAtencion = false;
        }
        if(servidorFinalizacion.getTipoAtencion() == TipoAtencion.Recepcion){
            continuarAtencion = false;
        }


        Paciente pacienteQueTermino = servidorFinalizacion.finalizarAtencion( continuarAtencion );
        //System.out.println("Evento. paso : " +  evento.getServidor().toString());
        boolean servidorVacio = servidorFinalizacion.getLongitud() == -1;

        if(evento.getTipoEvento() == TipoEvento.FIN_ATENCION_EMERGENCIA){
            acumuladorTiempoEsperaPacientesEmergencia += pacienteQueTermino.getTiempoEspera();
        }

        if(!servidorVacio){
            double tiempo = 0;
            TipoEvento tipoEventoFinAtencion = null;
            if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.General){
                tiempo = generadorFinAtencionGeneral.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_GENERAL;
            } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Emergencia){
                tiempo = generadorFinAtencionEmergencia.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_EMERGENCIA;
            } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Especialista){
                tiempo = generadorFinAtencionEspecialista.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_ESPECIALIDAD;
            } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Terapia){
                tiempo = generadorFinAtencionTerapia.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_TERAPIA;
            } else if (evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Recepcion){
                tiempo = generadorFinAtencionRecepcion.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_RECEPCION;
            }
            double tiempoProximoEvento = tiempo + reloj;
            Evento sigEvento = new Evento(tiempoProximoEvento, tipoEventoFinAtencion, evento.getServidor());
            eventos.add(sigEvento);
        }

        if(evento.getTipoEvento() != TipoEvento.FIN_ATENCION_RECEPCION && continuarAtencion){
            List<Servidor> serviroresTipoCorrespondiente = servidores.stream().filter(
                    servidor -> {
                        boolean b = servidor.getTipoAtencion() == TipoAtencion.Recepcion;
                        return b;
                    }
            ).collect(Collectors.toList());

            Servidor servidorCorrespondiente = serviroresTipoCorrespondiente.stream().min(
                    Comparator.comparing(Servidor::getLongitud)
            ).orElse(null);
            Estado estadoDelServidor = servidorCorrespondiente.getEstado();
            servidorCorrespondiente.añadirCola(pacienteQueTermino);

            if(estadoDelServidor == Estado.LIBRE){
                double tiempo = generadorFinAtencionRecepcion.getValor();
                double tiempoProximoEvento = tiempo + reloj;

                Evento sigEvento = new Evento(tiempoProximoEvento, TipoEvento.FIN_ATENCION_RECEPCION, servidorCorrespondiente);
                eventos.add(sigEvento);
            }

        }else{
            contadorPacientesAtendidos = contadorPacientesAtendidos + 1;
            acumuladorTiempoEsperaPacientes = acumuladorTiempoEsperaPacientes + pacienteQueTermino.getTiempoEspera();
        }
        actualizarTiemposEspera();
    }

    private void actualizarTiemposEspera(){
        servidores.stream().forEach(
                servidor -> { servidor.actualizarTiempoEsperaPacientes(tiempoDesdeAnteriorEvento); }
        );
    }

    private void armarVector(){
        double proxLlegadaGeneral = 0;
        double proxLlegadaEmergencia = 0;
        double proxLlegadaTerapia = 0;
        double proxLlegadaEspecialidad = 0;

        for (Evento evento : eventos) {
            TipoEvento tipo = evento.getTipoEvento();

            switch (tipo) {
                case LLEGADA_PACIENTE_GENERAL:
                    proxLlegadaGeneral = evento.getTiempo();
                    break;
                case LLEGADA_PACIENTE_EMERGENCIA:
                    proxLlegadaEmergencia = evento.getTiempo();
                    break;
                case LLEGADA_PACIENTE_TERAPIA:
                    proxLlegadaTerapia = evento.getTiempo();
                    break;
                case LLEGADA_PACIENTE_ESPECIALIDAD:
                    proxLlegadaEspecialidad = evento.getTiempo();
                    break;
            }
        }

        vectorEstado.setLlegadaGeneral_ProximaLLegada(String.valueOf(proxLlegadaGeneral));
        vectorEstado.setLlegadaEmergencia_ProximaLlegada(String.valueOf(proxLlegadaEmergencia));
        vectorEstado.setLlegadaTerapia_ProximaLlegada(String.valueOf(proxLlegadaTerapia));
        vectorEstado.setLlegadaEspecialista_ProximaLlegada(String.valueOf(proxLlegadaEspecialidad));

        for (Servidor servidor : servidores) {
            for (Paciente paciente : servidor.getCola()) {
                vectorEstado.addEstado_Espera_Paciente(paciente.getEstado(), String.valueOf(paciente.getTiempoEspera()), paciente.getTipoAtencion().getNombre());
            }

            if (servidor.getTipoAtencion() == TipoAtencion.General) {
                if (servidor.getId() == 1) {
                    vectorEstado.setCola_Medico_General_1(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_General_1(servidor.getEstado().toString());
                }
                if (servidor.getId() == 2) {
                    vectorEstado.setCola_Medico_General_2(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_General_2(servidor.getEstado().toString());
                }
                if (servidor.getId() == 3) {
                    vectorEstado.setCola_Medico_General_3(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_General_3(servidor.getEstado().toString());
                }
            }

            if (servidor.getTipoAtencion() == TipoAtencion.Emergencia) {
                if (servidor.getId() == 1) {
                    vectorEstado.setCola_Medico_Emergencia_1(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Emergencia_1(servidor.getEstado().toString());
                }
                if (servidor.getId() == 2) {
                    vectorEstado.setCola_Medico_Emergencia_2(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Emergencia_2(servidor.getEstado().toString());
                }
            }

            if (servidor.getTipoAtencion() == TipoAtencion.Especialista) {
                if (servidor.getId() == 1) {
                    vectorEstado.setCola_Medico_Especialista_1(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Especialista_1(servidor.getEstado().toString());
                }
                if (servidor.getId() == 2) {
                    vectorEstado.setCola_Medico_Especialista_2(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Especialista_2(servidor.getEstado().toString());
                }
                if (servidor.getId() == 3) {
                    vectorEstado.setCola_Medico_Especialista_3(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Especialista_3(servidor.getEstado().toString());
                }
                if (servidor.getId() == 4) {
                    vectorEstado.setCola_Medico_Especialista_4(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Especialista_4(servidor.getEstado().toString());
                }
            }

            if (servidor.getTipoAtencion() == TipoAtencion.Terapia) {
                if (servidor.getId() == 1) {
                    vectorEstado.setCola_Medico_Fisico_1(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Fisico_1(servidor.getEstado().toString());
                }
                if (servidor.getId() == 2) {
                    vectorEstado.setCola_Medico_Fisico_2(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Medico_Fisico_2(servidor.getEstado().toString());
                }
            }

            if (servidor.getTipoAtencion() == TipoAtencion.Recepcion) {
                if (servidor.getId() == 1) {
                    vectorEstado.setCola_Recepcion(String.valueOf(servidor.getLongitud()));
                    vectorEstado.setEstado_Recepcion(servidor.getEstado().toString());
                }
            }
        }


        for (Evento evento : eventos) {
            if (evento.getTipoEvento() == TipoEvento.FIN_ATENCION_GENERAL) {
                if (evento.getServidor().getId() == 1) vectorEstado.setFin_Atencion_General_1_TiempoFin(String.valueOf(evento.getTiempo()));
                if (evento.getServidor().getId() == 2) vectorEstado.setFin_Atencion_General_2_TiempoFin(String.valueOf(evento.getTiempo()));
                if (evento.getServidor().getId() == 3) vectorEstado.setFin_Atencion_General_3_TiempoFin(String.valueOf(evento.getTiempo()));
            }
            if (evento.getTipoEvento() == TipoEvento.FIN_ATENCION_EMERGENCIA) {
                if (evento.getServidor().getId() == 1) vectorEstado.setFin_Atencion_Emergencia_1_TiempoFin(String.valueOf(evento.getTiempo()));
                if (evento.getServidor().getId() == 2) vectorEstado.setFin_Atencion_Emergencia_2_TiempoFin(String.valueOf(evento.getTiempo()));
            }
            if (evento.getTipoEvento() == TipoEvento.FIN_ATENCION_ESPECIALIDAD) {
                if (evento.getServidor().getId() == 1) vectorEstado.setFin_Atencion_Especialista_1_TiempoFin(String.valueOf(evento.getTiempo()));
                if (evento.getServidor().getId() == 2) vectorEstado.setFin_Atencion_Especialista_2_TiempoFin(String.valueOf(evento.getTiempo()));
                if (evento.getServidor().getId() == 3) vectorEstado.setFin_Atencion_Especialista_3_TiempoFin(String.valueOf(evento.getTiempo()));
                if (evento.getServidor().getId() == 4) vectorEstado.setFin_Atencion_Especialista_4_TiempoFin(String.valueOf(evento.getTiempo()));
            }
            if (evento.getTipoEvento() == TipoEvento.FIN_ATENCION_TERAPIA) {
                if (evento.getServidor().getId() == 1) vectorEstado.setFin_Atencion_Terapia_Fisica_1_TiempoFin(String.valueOf(evento.getTiempo()));
                if (evento.getServidor().getId() == 2) vectorEstado.setFin_Atencion_Terapia_Fisica_2_TiempoFin(String.valueOf(evento.getTiempo()));
            }
            if (evento.getTipoEvento() == TipoEvento.FIN_ATENCION_RECEPCION) {
                if (evento.getServidor().getId() == 1) vectorEstado.setFin_Atencion_Recepcion_1_TiempoFin(String.valueOf(evento.getTiempo()));
            }
        }
//        System.out.println(vectorEstado.toString());
        //System.out.println(vectorEstado);

        //ESTADISTICA: TIEMPO ESPERA PROMEDIO = acumTiempoEsperaPacientes / cantidadPacientesAtendidos
        //ESTADISTICA: Acum. tiempo espera para pacientes emergencia
        //ESTADISTICA: Tiempo Ocupado servidores. (Se guarda en un atributo de cada servidor)
        //ESTADISTICA: Porc. Ocupación Recepción = debería resolverse igual q la logica anterior, asi q no hay problema.
        //ESTADISTICA: Tamaño promedio cola Recepción == ?? NI IDEA COMO CALCULARLO

        //estadisticas inventadas por nosotrosssss

        //ESTADISTICA: Cant. pacientes atendidos (re-easy, y si pa q no la vamos a complicar)
        //ESTADISTICA: Porc. ocupación servidores emergencias
        //ESTADISTICA: Cantidad promedio de pacientes en el sistema ?? No se si es tan facil de calcular tengo q ver
        vectorAcumulador.add(vectorEstado);

    }

    public List<VectorEstado> getVectorEstadoAcumulado(){
        return vectorAcumulador;
    }

    public void limpiarVector() {
        vectorAcumulador.clear();

    }
}