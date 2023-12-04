package dev.pack.utils.excel;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    void generateExcel(HttpServletResponse response) throws IOException;
    void generateExcelCustomHeader(
            HttpServletResponse response,
            String sheetName,
            List<String> headers,
            List<List<Object>> data
    ) throws IOException;
}
