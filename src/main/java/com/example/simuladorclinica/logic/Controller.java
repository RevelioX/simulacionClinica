package com.example.simuladorclinica.logic;

import com.example.simuladorclinica.VectorEstado;
import com.example.simuladorclinica.clases.*;
import com.example.simuladorclinica.generators.Generador;
import com.example.simuladorclinica.generators.GeneradorNumerosExponencial;
import com.example.simuladorclinica.generators.GeneradorNumerosNormales;
import com.example.simuladorclinica.generators.GeneradorNumerosUniformes;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private VectorEstado vectorEstado;
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
        Servidor general1 = new Servidor(TipoAtencion.General);
        Servidor general2 = new Servidor(TipoAtencion.General);
        Servidor general3 = new Servidor(TipoAtencion.General);

        servidores.add(general1);
        servidores.add(general2);
        servidores.add(general3);

        Servidor emergencias1 = new Servidor(TipoAtencion.Emergencia);
        Servidor emergencias2 = new Servidor(TipoAtencion.Emergencia);

        servidores.add(emergencias1);
        servidores.add(emergencias2);

        Servidor especialista1 = new Servidor(TipoAtencion.Especialista);
        Servidor especialista2 = new Servidor(TipoAtencion.Especialista);
        Servidor especialista3 = new Servidor(TipoAtencion.Especialista);
        Servidor especialista4 = new Servidor(TipoAtencion.Especialista);

        servidores.add(especialista1);
        servidores.add(especialista2);
        servidores.add(especialista3);
        servidores.add(especialista4);

        Servidor terapia1 = new Servidor(TipoAtencion.Terapia);
        Servidor terapia2 = new Servidor(TipoAtencion.Terapia);

        servidores.add(terapia1);
        servidores.add(terapia2);

        Servidor recepcion = new Servidor(TipoAtencion.Recepcion);
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
            if (i > desdeDondeMostrar && i < desdeDondeMostrar + 300){
                seDebeMostrar = true;
                vectorEstado = new VectorEstado();
            }else{
                seDebeMostrar = false;
            }
            Evento eventoAResolver = eventos.remove();
            tiempoDesdeAnteriorEvento = reloj - eventoAResolver.getTiempo();
            reloj = eventoAResolver.getTiempo();
            resolverEvento(eventoAResolver);
            if (seDebeMostrar) {
                armarVector();
            }


        }

    }

    private void resolverEvento(Evento evento){
        System.out.println(evento.toString());
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
        boolean servidorVacio = servidorCorrespondiente.getEstado() == Estado.LIBRE;

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
        Servidor servidorFinalizacion = evento.getServidor();
        double randomRecepcion = generadorLlegadaRecepcion.getValor();
        boolean continuarAtencion;
        if (randomRecepcion < 0.25){
            continuarAtencion = true;
        }else{
            continuarAtencion = false;
        }
        if(servidorFinalizacion.esTipoAtencion(TipoAtencion.Recepcion)){
            continuarAtencion = false;
        }

        Paciente pacienteQueTermino = servidorFinalizacion.finalizarAtencion( continuarAtencion );

        boolean servidorVacio = servidorFinalizacion.getEstado() == Estado.LIBRE;

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

            Evento sigEvento = new Evento(tiempoProximoEvento, tipoEventoFinAtencion, servidorFinalizacion);
            eventos.add(sigEvento);
        }
        double valorRND = generadorFinAtencionRecepcion.getValor();
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
        //System.out.println(vectorEstado.toString());
    }

}
