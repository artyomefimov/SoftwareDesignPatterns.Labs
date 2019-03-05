package lab3.command;

import lab1.pupils.Student;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class WriteToRawCommand implements WriteCommand {
    @Override
    public void write(Student student, Writer writer) throws IOException {
        System.out.println("Writing " + student.getSurname() + " to a row");
        StringBuilder stringBuilder = new StringBuilder(student.getSurname())
                .append(" ")
                .append(Arrays.toString(student.getMarks()))
                .append(" ")
                .append(Arrays.toString(student.getSubjects()))
                .append(" ");
        writer.write(stringBuilder.toString());
    }
}
