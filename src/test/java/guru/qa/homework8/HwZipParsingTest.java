package guru.qa.homework8;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;
/*
1.Задание 1
– Запаковать в zip архив несколько разных файлов - pdf, xlsx, csv
– Положить его в ресурсы
– Реализовать чтение и проверку содержимого каждого файла из архива
 */
public class HwZipParsingTest {
    ClassLoader cl = HwZipParsingTest.class.getClassLoader();

    @Test
    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("hw8/homework8.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel
                            .getSheetAt(0)
                            .getRow(0)
                            .getCell(0)
                            .getStringCellValue())
                            .contains("четверг");
                } else if (entry.getName().contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Lorem Ipsum - это текст");
                } else if (entry.getName().contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(1)[1]).contains("Harry Potter");
                }
            }
        }
    }

}
