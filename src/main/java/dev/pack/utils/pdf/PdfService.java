package dev.pack.utils.pdf;

import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface PdfService {
    void generate(HttpServletResponse response) throws DocumentException, IOException;
}
