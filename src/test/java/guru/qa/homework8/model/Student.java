package guru.qa.homework8.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Student {
    public String firstName;
    public String lastName;
    public int age;
    @JsonProperty("full-time_student")
    public boolean isFullTimeStudent;
    public List<String> lessons;
    @JsonProperty("student_card")
    public StudentCard studentCard;

    public static class StudentCard {
        public int number;
        public String issueDate;
    }
}





