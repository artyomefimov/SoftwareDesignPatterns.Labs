package lab1.pupils;

import lab1.exceptions.DuplicateSubjectException;
import lab1.exceptions.MarkOutOfBoundsException;
import lab3.visitor.Visitor;

public interface Pupil {
    String getSurname();

    void setSurname(String surname);

    int getMark(int index);

    int[] getMarks();

    void setMark(int index, int value) throws MarkOutOfBoundsException;

    String getSubject(int index);

    String[] getSubjects();

    void setSubject(int index, String newSubject) throws DuplicateSubjectException;

    int getArrayLength();

    void addMarkAndSubject(int mark, String subject) throws DuplicateSubjectException, MarkOutOfBoundsException;

    void visit(Visitor visitor);
}
