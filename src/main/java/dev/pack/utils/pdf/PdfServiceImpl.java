package dev.pack.utils.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import dev.pack.modules.student.Student;
import dev.pack.modules.student.StudentRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfServiceImpl implements PdfService{

    private final StudentRepository studentRepository;

    @Override
    public void generate(HttpServletResponse response) throws DocumentException, IOException {
        List<Student> students = this.studentRepository.findAll();

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraph = new Paragraph("List of students", fontTitle);

        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);

        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3});
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Origin School", font));
        table.addCell(cell);

        // Iterating over the list of students
        for (Student student : students) {
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getName());
            table.addCell(student.getSchool_origin());
        }

        document.add(table);
        document.close();

    }
}
