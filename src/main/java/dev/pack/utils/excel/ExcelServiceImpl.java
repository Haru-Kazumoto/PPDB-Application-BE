package dev.pack.utils.excel;

import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final StudentRepository studentRepository;

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Student> students = studentRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Data siswa");

        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Nomor telepon");
        row.createCell(2).setCellValue("Nama siswa");
        row.createCell(3).setCellValue("Jenjang siswa");
        row.createCell(4).setCellValue("Jenis kelamin");
        row.createCell(5).setCellValue("NISN");

        int dataRowIndex = 1;

        for(Student student : students){
            HSSFRow dataRow = sheet.createRow(dataRowIndex);

            dataRow.createCell(0).setCellValue(student.getId());
            dataRow.createCell(1).setCellValue(student.getPhone());
            dataRow.createCell(2).setCellValue(student.getName());
            dataRow.createCell(3).setCellValue(student.getGrade().ordinal());
            dataRow.createCell(4).setCellValue(student.getGender());
            dataRow.createCell(5).setCellValue(student.getNisn());

            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();

        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

    @Override
    public void generateExcelCustomHeader(HttpServletResponse response,String sheetName, List<String> headers, List<List<Object>> data) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //Style header
        HSSFCellStyle headerStyle = workbook.createCellStyle();

        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.AQUA.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(20);

        HSSFFont font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);

        for(int i = 0; i < headers.size(); i++){
            headerRow.createCell(i).setCellValue(headers.get(i));
        }

        int dataRowIndex = 1;

        for(List<Object> rowData : data){
            HSSFRow dataRow = sheet.createRow(dataRowIndex);

            for(int i = 0; i < rowData.size(); i++){
                HSSFCell cell = dataRow.createCell(i);
                Object value = rowData.get(i);

                if(value instanceof String){
                    cell.setCellValue((String) value);
                } else if (value instanceof Integer){
                    cell.setCellValue((Integer) value);
                }
            }

            dataRowIndex++;
        }

        ServletOutputStream outputStream = response.getOutputStream();

        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
