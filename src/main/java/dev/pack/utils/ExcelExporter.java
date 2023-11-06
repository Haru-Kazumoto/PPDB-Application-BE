package dev.pack.utils;

import dev.pack.modules.student.Student;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

@Component
public class ExcelExporter {

    private List<Student> studentList;
    private XSSFSheet xssfSheet;
    private XSSFWorkbook workbook;

    public ExcelExporter(List<Student> studentList) {
        this.studentList = studentList;

        workbook = new XSSFWorkbook();
    }

    public void writeHeader(){
        xssfSheet = workbook.createSheet("Data Siswa");
        Row row = xssfSheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setBold(true);
        font.setFontHeight(16);

        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Nama siswa", style);
        createCell(row, 2, "Nomor telepon", style);
        createCell(row, 3, "Jenis kelamin", style);
        createCell(row, 4, "Agama", style);
        createCell(row, 5, "Tempat lahir", style);
        createCell(row, 6, "Tanggal lahir", style);
        createCell(row, 7, "Alamat", style);
        createCell(row,  8,"Jurusan siswa", style);
    }

    public void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style){
        xssfSheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }

        cell.setCellStyle(style);
    }

    public void write(){
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(14);
        style.setFont(font);

        for(Student record : studentList){
            Row row = xssfSheet.createRow(rowCount++);

            int columnCount = 0;

            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getName(), style);
            createCell(row, columnCount++, record.getPhone(), style);
            createCell(row, columnCount++, record.getGender(), style);
            createCell(row, columnCount++, record.getReligion(), style);
            createCell(row, columnCount++, record.getBirth_place(), style);
            createCell(row, columnCount++, record.getBirth_date(), style);
            createCell(row, columnCount++, record.getAddress(), style);
            createCell(row, columnCount++, record.getMajor(), style);
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader();
        write();
        ServletOutputStream outputStream = response.getOutputStream();

        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
