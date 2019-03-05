package lab3.visitor;

import lab1.pupils.Schoolboy;
import lab1.pupils.Student;

public interface Visitor {
    void visit(Student student);
    void visit(Schoolboy schoolboy);
}
