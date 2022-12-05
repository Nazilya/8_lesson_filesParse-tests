package guru.qa.homework8;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class HwFilesParsingTest {

    ClassLoader cl = HwFilesParsingTest.class.getClassLoader();

    @Test
    void pdfParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("hw8/Lorem.pdf");
        ) {
            PDF content = new PDF(resource);
            assertThat(content.text).contains("Lorem Ipsum - это текст-\"рыба\"");
        }
    }

    @Test
    void xlsParseTest() throws Exception {
        try (InputStream resourceAsStream = cl.getResourceAsStream("hw8/December.xlsx")) {
            XLS content = new XLS(resourceAsStream);
            System.out.println("");
            assertThat(content.excel
                    .getSheetAt(0)
                    .getRow(1)
                    .getCell(0)
                    .getStringCellValue())
                    .contains("пятница");
        }
    }

    @Test
    void csvParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("hw8/Books.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(resource))
        ) {
            List<String[]> content = reader.readAll();
            assertThat(content.get(1)[1]).contains("Harry Potter");
        }
    }

    //Разбор json  файла библиотекой Gson - д/быть подключена зав-сть 'com.google.code.gson:gson:2.10'
//    @Test
//    void jsonParseWithGsonTest() throws Exception {
//        Gson gson = new Gson();
//        try (
//                InputStream resource = cl.getResourceAsStream("hw8/student.json");
//                InputStreamReader reader = new InputStreamReader(resource)
//        ) {
//            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
//            assertThat(jsonObject.get("firstName").getAsString()).isEqualTo("Ivan");
//            assertThat(jsonObject.get("full-time_student").getAsBoolean()).isTrue();
//            assertThat(jsonObject.get("student_card").getAsJsonObject().get("number").getAsString()).isEqualTo("123456");
//
//        }
//    }
//
//    @Test
//    void jsonParseWithGsonModelTest() throws Exception {
//        Gson gson = new Gson();
//        try (
//                InputStream resource = cl.getResourceAsStream("hw8/student.json");
//                InputStreamReader reader = new InputStreamReader(resource)
//        ) {
//            Student student = gson.fromJson(reader, Student.class);
//            assertThat(student.firstName).isEqualTo("Ivan");
//            assertThat(student.fullTimeStudent).isTrue();
//            assertThat(student.studentCard.number).isEqualTo(123456);
//        }
//    }

}