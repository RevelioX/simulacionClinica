package com.example.simuladorclinica.logic;

import com.example.simuladorclinica.VectorEstado;
import com.example.simuladorclinica.clases.*;
import com.example.simuladorclinica.ecuDiferencial.ResolverEcDiferencial;
import com.example.simuladorclinica.generators.Generador;
import com.example.simuladorclinica.generators.GeneradorNumerosExponencial;
import com.example.simuladorclinica.generators.GeneradorNumerosUniformes;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component

public class Controller {
    private VectorEstado vectorEstado = new VectorEstado();
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

    private Generador generadorInterrupcion;

    private int lineasSimular;

    private int desdeDondeMostrar;

    boolean seDebeMostrar;

    final int VALOR_T = 2;

    //Datos Estadisticossss
    private int contadorPacientesAtendidos;
    private double acumuladorTiempoEsperaPacientes;
    private double acumuladorTiempoEsperaPacientesEmergencia;

    private int acumuladorCantidadPacientesEmergencia;

    private int acumuladorEdadesPacientes;

    private int cantidadLlegadas;

    private int cantidadLlegadasConSistemaLleno;
    private int cantidadMedicosGenerales;

    public void prepararSimulacion(int lineasSimular, int desdeDondeMostrar, double mediaLlegadaGeneral, double mediaLlegadaEmergencia, double mediaLlegadaEspecialista, double mediaLlegadaTerapia, double mediaAtencionGeneral, double mediaAtencionEmergencia, double mediaAtencionEspecialidad, double mediaAtencionTerapia, double mediaAtencionRecepcion, int cantidadMedicosGenerales){
        this.desdeDondeMostrar = desdeDondeMostrar;
        this.lineasSimular = lineasSimular;

        this.cantidadMedicosGenerales = cantidadMedicosGenerales;

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

        generadorInterrupcion = new GeneradorNumerosUniformes(0,1);
        generadorInterrupcion.generarValor(1000);

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
        for (int i = 1; i <= cantidadMedicosGenerales; i++) {
            Servidor general = new Servidor(TipoAtencion.General, i);
            servidores.add(general);
        }

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
        Evento eventoLlegadaGeneral = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_GENERAL, reloj);

        tiempo = generadorLlegadasEmergencia.getValor();
        Evento eventoLlegadaEmergencia = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_EMERGENCIA, reloj);

        tiempo = generadorLlegadasEspecialista.getValor();
        Evento eventoLlegadaEspecialista = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_ESPECIALISTA, reloj);

        tiempo = generadorLlegadaTerapia.getValor();
        Evento eventoLlegadaTerapia = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_TERAPIA, reloj);

        eventos.add(eventoLlegadaGeneral);
        eventos.add(eventoLlegadaEspecialista);
        eventos.add(eventoLlegadaEmergencia);
        eventos.add(eventoLlegadaTerapia);

        double numeroAleatorio = generadorInterrupcion.getValor();
        double tiempoProximaInterrupcion;
        if(numeroAleatorio < 0.60){
            tiempoProximaInterrupcion = VALOR_T * 6;
        }else if( numeroAleatorio < 0.8){
            tiempoProximaInterrupcion = VALOR_T * 4;
        } else {
            tiempoProximaInterrupcion = VALOR_T * 8;
        }

        double duracionInterrupcion;
        duracionInterrupcion = ResolverEcDiferencial.resolverEcuacion("0.025*x-0.5*y-12.85");

        Evento proxInterrupcion = new Evento(reloj + tiempoProximaInterrupcion, TipoEvento.INTERRUPCION, reloj);
        proxInterrupcion.setTiempoEnfriamentoLlave(reloj + tiempoProximaInterrupcion + duracionInterrupcion);

        eventos.add(proxInterrupcion);
    }
    public void simular(){
        for(int i = 0; i < lineasSimular; i++){
            if ((i >= desdeDondeMostrar && i < desdeDondeMostrar + 50) || i == lineasSimular - 1){
                seDebeMostrar = true;
                vectorEstado = new VectorEstado();
                vectorEstado.setNroIteracion(String.valueOf(i));
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
        }else if(tipoEvento.esDeInterrupcion()) {
            resolverInterrupcion(evento);
        } else if(tipoEvento.esDeFinInterrupcion()){
            resolverFinInterrupcion(evento);
        }
    }

    private boolean estanTodosLosServidoresOcupados(TipoAtencion tipoAtencion){
        for(Servidor servidor: servidores){
            if(servidor.getTipoAtencion() == tipoAtencion){
                if(servidor.getEstado() == Estado.LIBRE){
                    return false;
                }
            }
        }
        return true;
    }

    private void resolverInterrupcion(Evento evento) {
        if (seDebeMostrar) vectorEstado.setTiempoFinalizacionInterrupcion(String.valueOf(evento.getTiempoEnfriamentoLlave()));

        // Crear una lista temporal para almacenar los eventos modificados
        List<Evento> eventosModificados = new ArrayList<>();

        // Iterar sobre la lista original de eventos
        for (Evento eventoActual : eventos) {
            // Verificar si el evento actual es de tipo FIN_ATENCION_ESPECIALIDAD
            if (eventoActual.getTipoEvento() == TipoEvento.FIN_ATENCION_ESPECIALISTA) {
                // Calcular el nuevo tiempo
                double nuevoTiempo = ( eventoActual.getTiempo() - reloj ) + evento.getTiempoEnfriamentoLlave();

                // Actualizar el tiempo del evento actual
                eventoActual.setTiempo(nuevoTiempo);

                // Agregar el evento actual a la lista de eventos modificados
                eventosModificados.add(eventoActual);
            }
        }

        // Remover todos los eventos de tipo FIN_ATENCION_ESPECIALIDAD de la lista original
        eventos.removeAll(eventosModificados);

        // Agregar el evento finalización a la lista de eventos
        eventos.addAll(eventosModificados);
        Evento eventoFinalizacion = new Evento(evento.getTiempoEnfriamentoLlave(), TipoEvento.FIN_INTERRUPCION, reloj);
        eventos.add(eventoFinalizacion);

        // Actualizar los tiempos de espera
        actualizarTiemposEspera();
    }

    private void resolverFinInterrupcion(Evento evento){
        double tiempoProximaInterrupcion;
        double numeroAleatorio = generadorInterrupcion.getValor();
        if(numeroAleatorio < 0.60){
            tiempoProximaInterrupcion = VALOR_T * 6;
        }else if( numeroAleatorio < 0.8){
            tiempoProximaInterrupcion = VALOR_T * 4;
        } else {
            tiempoProximaInterrupcion = VALOR_T * 8;
        }

        if(seDebeMostrar) vectorEstado.setTiempoProximaInterrupcion(String.valueOf(tiempoProximaInterrupcion + reloj));

        double duracionInterrupcion;
        duracionInterrupcion = ResolverEcDiferencial.resolverEcuacion("0.025*x-0.5*y-12.85");

        Evento proxInterrupcion = new Evento(reloj + tiempoProximaInterrupcion, TipoEvento.INTERRUPCION, reloj);
        proxInterrupcion.setTiempoEnfriamentoLlave(reloj + tiempoProximaInterrupcion + duracionInterrupcion);
        eventos.add(proxInterrupcion);
        actualizarTiemposEspera();
    }

    private void resolverEventoLlegada(Evento evento){
        cantidadLlegadas += 1;
        if(estanTodosLosServidoresOcupados(evento.getTipoEvento().getTipoAtencion())){
            cantidadLlegadasConSistemaLleno += 1;
        }
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

        Evento sigEvento = new Evento(tiempoProximoEvento, evento.getTipoEvento(), reloj );
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

            }  else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Terapia){
                tiempo = generadorFinAtencionTerapia.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_TERAPIA;

            } else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Especialista){
                tiempo = generadorFinAtencionEspecialista.getValor();
                tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_ESPECIALISTA;

            }

            tiempoProximoEvento = tiempo + reloj;

            sigEvento = new Evento(tiempoProximoEvento, tipoEventoFinAtencion, servidorCorrespondiente, reloj);
            eventos.add(sigEvento);
        }
        actualizarTiemposEspera();
    }

    private void resolverFinAtencion(Evento evento){
        //System.out.println(contadorPacientesAtendidos);
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
            acumuladorCantidadPacientesEmergencia += 1;
        }

        if(!servidorVacio){
            double tiempo = 0;
            TipoEvento tipoEventoFinAtencion = null;
            tipoEventoFinAtencion = evento.getTipoEvento();

            //if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.General){
            //    tiempo = generadorFinAtencionGeneral.getValor();
            //    tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_GENERAL;
            //
            //
            //} else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Emergencia){
            //    tiempo = generadorFinAtencionEmergencia.getValor();
            //    tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_EMERGENCIA;
            //
            //} else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Especialista){
            //    tiempo = generadorFinAtencionEspecialista.getValor();
            //    tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_ESPECIALIDAD;
            //
            //} else if(evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Terapia){
            //    tiempo = generadorFinAtencionTerapia.getValor();
            //    tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_TERAPIA;
            //
            //} else if (evento.getTipoEvento().getTipoAtencion() == TipoAtencion.Recepcion){
            //    tiempo = generadorFinAtencionRecepcion.getValor();
            //    tipoEventoFinAtencion = TipoEvento.FIN_ATENCION_RECEPCION;
            //
            //}
            double tiempoProximoEvento = tiempo + reloj;
            Evento sigEvento = new Evento(tiempoProximoEvento, tipoEventoFinAtencion, evento.getServidor(),  reloj);
            eventos.add(sigEvento);
        }

        if(evento.getTipoEvento() != TipoEvento.FIN_ATENCION_RECEPCION && continuarAtencion){
            if(seDebeMostrar) vectorEstado.setResultado_Recepcion("Pasa Recepción");
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

                Evento sigEvento = new Evento(tiempoProximoEvento, TipoEvento.FIN_ATENCION_RECEPCION, servidorCorrespondiente, reloj);
                eventos.add(sigEvento);
            }

        }else{
            contadorPacientesAtendidos = contadorPacientesAtendidos + 1;
            acumuladorEdadesPacientes = acumuladorEdadesPacientes + pacienteQueTermino.getEdad();
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
                case LLEGADA_PACIENTE_ESPECIALISTA:
                    proxLlegadaEspecialidad = evento.getTiempo();
                    break;
            }
        }

        vectorEstado.setLlegadaGeneral_ProximaLLegada(String.valueOf(proxLlegadaGeneral));
        vectorEstado.setLlegadaEmergencia_ProximaLlegada(String.valueOf(proxLlegadaEmergencia));
        vectorEstado.setLlegadaTerapia_ProximaLlegada(String.valueOf(proxLlegadaTerapia));
        vectorEstado.setLlegadaEspecialista_ProximaLlegada(String.valueOf(proxLlegadaEspecialidad));

        double tiempoOcupadoServidores = 0;

        double tiempoOcupadoRecepcion = 0;
        double tiempoOcupadoGeneral = 0;
        double tiempoOcupadoEmergencia = 0;
        double tiempoOcupadoEspecialidad = 0;
        double tiempoOcupadoTerapia = 0;

        for (Servidor servidor : servidores) {
            tiempoOcupadoServidores += servidor.getTiempoOcupacion();

            for (Paciente paciente : servidor.getCola()) {
                vectorEstado.addEstado_Espera_Paciente(paciente);
            }

            if (servidor.getTipoAtencion() == TipoAtencion.General) {
                tiempoOcupadoGeneral += servidor.getTiempoOcupacion();
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
                tiempoOcupadoEmergencia += servidor.getTiempoOcupacion();
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
                tiempoOcupadoEspecialidad += servidor.getTiempoOcupacion();
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
                tiempoOcupadoTerapia += servidor.getTiempoOcupacion();
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
                tiempoOcupadoRecepcion += servidor.getTiempoOcupacion();
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
            if (evento.getTipoEvento() == TipoEvento.FIN_ATENCION_ESPECIALISTA) {
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
        //TIEMPO ESPERA PROMEDIO
        vectorEstado.setCantidadPacientesAtendidos(String.valueOf(contadorPacientesAtendidos));
        vectorEstado.setAcumuladorTiempoEspera(String.valueOf(acumuladorTiempoEsperaPacientes));
        vectorEstado.setTiempoEsperaPromedio(String.valueOf(acumuladorTiempoEsperaPacientes/contadorPacientesAtendidos));
        //PORCENTAJE OCUPACION PARA CADA TIPO DE SERVICIO

        vectorEstado.setTiempoOcupacionRecepcion(String.valueOf(tiempoOcupadoRecepcion));
        vectorEstado.setPorcentajeOcupacionRecepcion(String.valueOf((tiempoOcupadoRecepcion/reloj) * 100));
        vectorEstado.setTiempoOcupacionGeneral(String.valueOf(tiempoOcupadoGeneral));
        vectorEstado.setPorcentajeOcupacionGeneral(String.valueOf(tiempoOcupadoGeneral/(reloj*3) * 100));
        vectorEstado.setTiempoOcupacionEmergencia(String.valueOf(tiempoOcupadoEmergencia));
        vectorEstado.setPorcentajeOcupacionEmergencia(String.valueOf(((tiempoOcupadoEmergencia) / (2*reloj)) * 100));
        vectorEstado.setTiempoOcupacionEspecialidad(String.valueOf(tiempoOcupadoEspecialidad));
        vectorEstado.setPorcentajeOcupacionEspecialidad(String.valueOf(((tiempoOcupadoEspecialidad / (4*reloj)) * 100)));
        vectorEstado.setTiempoOcupacionTerapia(String.valueOf(tiempoOcupadoTerapia));
        vectorEstado.setPorcentajeOcupacionTerapia(String.valueOf(((tiempoOcupadoTerapia / (2*reloj)) * 100)));

        //Tiempo promedio de emergencia promedio
        vectorEstado.setAcumuladorTiempoEsperaPacientesEmergencia(String.valueOf(acumuladorTiempoEsperaPacientesEmergencia));
        vectorEstado.setAcumuladorTiempoOcupadoServidores(String.valueOf(tiempoOcupadoServidores));
        vectorEstado.setTiempoEsperaPacientesEmergenciaPromedio(String.valueOf(acumuladorTiempoEsperaPacientesEmergencia/acumuladorCantidadPacientesEmergencia));

        //Edades promedio de pacientes atendidos
        vectorEstado.setAcumuladorEdadesPacientesAtendidos(String.valueOf(acumuladorEdadesPacientes));
        if( contadorPacientesAtendidos != 0){
            vectorEstado.setPromedioEdadesPacientesAtendidos(String.valueOf(acumuladorEdadesPacientes/contadorPacientesAtendidos));
        }

        //Cantidad pacientes atendidos x hora
        vectorEstado.setCantidadPacientesAtendidosPorHora(String.valueOf(contadorPacientesAtendidos/reloj));

        //Probabilidad de que un paciente llegue y se encuentre el servicio lleno.
        double probLlegadasSistLleno = (double) cantidadLlegadasConSistemaLleno / cantidadLlegadas;
        vectorEstado.setProbabilidadLlegadaConServicioLleno(String.valueOf(probLlegadasSistLleno));

//        System.out.println(vectorEstado.toString());
        //System.out.println(vectorEstado);

        //ESTADISTICA: TIEMPO ESPERA PROMEDIO = acumTiempoEsperaPacientes / cantidadPacientesAtendidos
        //ESTADISTICA: Acum. tiempo espera para pacientes emergencia
        //ESTADISTICA: Tiempo Ocupado servidores. (Se guarda en un atributo de cada servidor)
        //ESTADISTICA: Porc. Ocupación Recepción = debería resolverse igual q la logica anterior, asi q no hay problema.
        //tiempo ocupado / tiempo total.
        //Estadistica: Porc. Ocupacion General
        //Estadistica: Porc. Ocupacion Especialidad
        //Estadistica: Porc. Ocupacion Emergencias
        //Estadistica: Porc. Ocupacion Terapia
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