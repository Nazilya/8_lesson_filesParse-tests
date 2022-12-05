package guru.qa.homework8;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.homework8.model.Student;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

/*
2. Задание 2
– Реализовать разбор json  файла библиотекой Jackson https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.13.1
– Придумать реальный объект и описать его в виде  json
– В идеале json должен содержать массив
 */
public class HwJsonParseByJacksonTest {
    ClassLoader cl = HwZipParsingTest.class.getClassLoader();

    @Test
    void jsonParseTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                InputStream resource = cl.getResourceAsStream("hw8/student.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Student student = objectMapper.readValue(reader, Student.class);
            assertThat(student.firstName).isEqualTo("Ivan");
            assertThat(student.isFullTimeStudent).isTrue();
            assertThat(student.studentCard.number).isEqualTo(123456);
            assertThat(student.lessons.get(1)).isEqualTo("English");

        }
    }


    @Test
    void jsonParseTest4() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/hw8/student.json");
        Student student = objectMapper.readValue(file, Student.class);
        assertThat(student.lastName).isEqualTo("Petrov");
        assertThat(student.isFullTimeStudent).isTrue();
        assertThat(student.studentCard.issueDate).isEqualTo("01.09.2020");
        assertThat(student.lessons.get(2)).isEqualTo("Economics");

    }

}

