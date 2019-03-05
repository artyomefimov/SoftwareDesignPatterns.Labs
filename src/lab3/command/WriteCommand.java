package lab3.command;

import lab1.pupils.Student;

import java.io.IOException;
import java.io.Writer;

public interface WriteCommand {
    void write(Student student, Writer writer) throws IOException;
}
