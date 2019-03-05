package lab3.command;

import lab1.pupils.Student;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class WriteToColumnCommand implements WriteCommand {
    @Override
    public void write(Student student, Writer writer) throws IOException {
        System.out.println("Writing " + student.getSurname() + " to a column");
        StringBuilder stringBuilder = new StringBuilder(student.getSurname())
                .append("\n")
                .append(Arrays.toString(student.getMarks()))
                .append("\n")
                .append(Arrays.toString(student.getSubjects()))
                .append("\n");
        writer.write(stringBuilder.toString());
    }
}
