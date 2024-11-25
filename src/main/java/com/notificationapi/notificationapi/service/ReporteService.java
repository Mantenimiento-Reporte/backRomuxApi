package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.domain.PersonaDomain;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ReporteService {
    @Autowired
    private  PersonaService personaService;

    public Boolean generarReporte(String nombreArchivo) {
        List<PersonaDomain> personas = personaService.findAll();
        try {
            SXSSFWorkbook workbook = new SXSSFWorkbook(1);
            SXSSFSheet sheet = workbook.createSheet();
            Row nRow = null;
            Cell nCell = null;

            //generar cabecera
            Object[] objetoHEAD = {"NOMBRE" , "APELLIDO" , "CORREO ELECTRONICO"};
            nRow = sheet.createRow(0);
            for (int i = 0; i < objetoHEAD.length; i++) {
                nCell = nRow.createCell(i);
                nCell.setCellValue(objetoHEAD[i].toString());
            }

            //generar el cuerpo
            Iterator<PersonaDomain> it = personas.iterator();
            int pagerow = 1;
            int itera = 0 ;
            while (it.hasNext()) {
                PersonaDomain persona = it.next();
                nRow = sheet.createRow(pagerow++);
                nCell = nRow.createCell(0);
                nCell.setCellValue(persona.getPrimerNombre());
                nCell = nRow.createCell(1);
                nCell.setCellValue(persona.getPrimerApellido());
                nCell = nRow.createCell(2);
                nCell.setCellValue(persona.getCorreoElectronico());
            }

            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            workbook.dispose();
            return true;


        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
