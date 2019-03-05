package lab3.visitor;

import lab1.pupils.Schoolboy;
import lab1.pupils.Student;

import java.util.Arrays;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Student student) {
        StringBuilder stringBuilder = new StringBuilder(student.getSurname())
                .append(" ")
                .append(Arrays.toString(student.getMarks()))
                .append(" ")
                .append(Arrays.toString(student.getSubjects()))
                .append(" ");
        System.out.println(stringBuilder.toString());
    }

    @Override
    public void visit(Schoolboy schoolboy) {
        StringBuilder stringBuilder = new StringBuilder(schoolboy.getSurname())
                .append("\n")
                .append(Arrays.toString(schoolboy.getMarks()))
                .append("\n")
                .append(Arrays.toString(schoolboy.getSubjects()))
                .append("\n");
        System.out.println(stringBuilder.toString());
    }
}
