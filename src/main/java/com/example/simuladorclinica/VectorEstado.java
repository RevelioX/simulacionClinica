package com.example.simuladorclinica;

import com.example.simuladorclinica.clases.Paciente;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class VectorEstado {
    String evento;
    String reloj;
    String LlegadaGeneral_ProximaLLegada;
    String LlegadaEmergencia_ProximaLlegada;
    String LlegadaEspecialista_ProximaLlegada;
    String LlegadaTerapia_ProximaLlegada;
    List<String> Estado_Espera_Paciente; // Estado - Espera - TipoAtencion En ese orden
    //-----------------------------------------------//
    String Fin_Atencion_General_1_TiempoFin;
    String Fin_Atencion_General_2_TiempoFin;
    String Fin_Atencion_General_3_TiempoFin;
    //--------------------------------------------//
    String Fin_Atencion_Emergencia_1_TiempoFin;
    String Fin_Atencion_Emergencia_2_TiempoFin;
    //--------------------------------------------//
    String Fin_Atencion_Especialista_1_TiempoFin;
    String Fin_Atencion_Especialista_2_TiempoFin;
    String Fin_Atencion_Especialista_3_TiempoFin;
    String Fin_Atencion_Especialista_4_TiempoFin;
    //--------------------------------------------//
    String Fin_Atencion_Terapia_Fisica_1_TiempoFin;
    String Fin_Atencion_Terapia_Fisica_2_TiempoFin;
    //--------------------------------------------//
    String Resultado_Recepcion;
    //--------------------------------------------//
    String Estado_Medico_General_1;
    String Cola_Medico_General_1;
    String Estado_Medico_General_2;
    String Cola_Medico_General_2;
    String Estado_Medico_General_3;
    String Cola_Medico_General_3;
    //--------------------------------------------//
    String Estado_Medico_Emergencia_1;
    String Cola_Medico_Emergencia_1;
    String Estado_Medico_Emergencia_2;
    String Cola_Medico_Emergencia_2;
    //--------------------------------------------//
    String Estado_Medico_Especialista_1;
    String Cola_Medico_Especialista_1;
    String Estado_Medico_Especialista_2;
    String Cola_Medico_Especialista_2;
    String Estado_Medico_Especialista_3;
    String Cola_Medico_Especialista_3;
    String Estado_Medico_Especialista_4;
    String Cola_Medico_Especialista_4;
    //--------------------------------------------//
    String Estado_Medico_Fisico_1;
    String Cola_Medico_Fisico_1;
    String Estado_Medico_Fisico_2;
    String Cola_Medico_Fisico_2;

    //--------------------------------------------//
    String Estado_Recepcion;
    String Cola_Recepcion;

    public void AgregarFilaVector() throws IOException{
        String filePath = "Vector_estado_Tp4.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        //TODO hay que agregar el numero de iteracion actual
        int rowIndex = sheet.getLastRowNum() + 1;

        Row row = sheet.createRow(rowIndex);
        //testing HardCodeado
        String evento = "Dato1";
        String reloj = "Dato2";
        String llegadaGeneral_ProximaLLegada = "Dato5";
        String llegadaEmergencia_ProximaLlegada = "Dato8";
        String llegadaEspecialista_ProximaLlegada = "Dato11";
        String llegadaTerapia_ProximaLlegada = "Dato14";
        List<String> estado_Espera_Paciente = List.of("Estado", "Espera", "Tipo", "Dato19", "Dato20", "Dato21", "Dato22");
        // Incrementar el siguiente paciente en uno

        String fin_Atencion_General_1_TiempoFin = "Dato16";
        String fin_Atencion_General_2_TiempoFin = "Dato17";
        String fin_Atencion_General_3_TiempoFin = "Dato18";

        String fin_Atencion_Emergencia_1_TiempoFin = "Dato19";
        String fin_Atencion_Emergencia_2_TiempoFin = "Dato20";

        String fin_Atencion_Especialista_1_TiempoFin = "Dato21";
        String fin_Atencion_Especialista_2_TiempoFin = "Dato22";
        String fin_Atencion_Especialista_3_TiempoFin = "Dato23";
        String fin_Atencion_Especialista_4_TiempoFin = "Dato24";

        String fin_Atencion_Terapia_Fisica_1_TiempoFin = "Dato25";
        String fin_Atencion_Terapia_Fisica_2_TiempoFin = "Dato26";

        int cellIndex = 0; // En el 0 va la iteracion y en el uno arranca con los datos
        row.createCell(cellIndex++).setCellValue(evento);
        row.createCell(cellIndex++).setCellValue(reloj);
        // Asume que los demás datos también se agregan de forma similar
        row.createCell(cellIndex++).setCellValue(llegadaGeneral_ProximaLLegada);
        row.createCell(cellIndex++).setCellValue(llegadaEmergencia_ProximaLlegada);
        row.createCell(cellIndex++).setCellValue(llegadaEspecialista_ProximaLlegada);
        row.createCell(cellIndex++).setCellValue(llegadaTerapia_ProximaLlegada);

        for (String estado : estado_Espera_Paciente) {
            row.createCell(cellIndex++).setCellValue(estado);
        }

        row.createCell(cellIndex++).setCellValue(fin_Atencion_General_1_TiempoFin);
        row.createCell(cellIndex++).setCellValue(fin_Atencion_General_2_TiempoFin);
        row.createCell(cellIndex++).setCellValue(fin_Atencion_General_3_TiempoFin);

        row.createCell(cellIndex++).setCellValue(fin_Atencion_Emergencia_1_TiempoFin);
        row.createCell(cellIndex++).setCellValue(fin_Atencion_Emergencia_2_TiempoFin);

        row.createCell(cellIndex++).setCellValue(fin_Atencion_Especialista_1_TiempoFin);
        row.createCell(cellIndex++).setCellValue(fin_Atencion_Especialista_2_TiempoFin);
        row.createCell(cellIndex++).setCellValue(fin_Atencion_Especialista_3_TiempoFin);
        row.createCell(cellIndex++).setCellValue(fin_Atencion_Especialista_4_TiempoFin);

        row.createCell(cellIndex++).setCellValue(fin_Atencion_Terapia_Fisica_1_TiempoFin);
        row.createCell(cellIndex++).setCellValue(fin_Atencion_Terapia_Fisica_2_TiempoFin);

        //todo - Esto hay que dividirlo asi queda el canal siempre abierto
        fis.close();

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);

        // Todo Esta parte llevarla al controller
        workbook.close();
        fos.close();
    }
    //todo - Aca faltaría lo relacionado a los eventos fin_atención pero hay q definirlo con Colo.


}
