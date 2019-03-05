package lab2.decorator;

import lab1.exceptions.DuplicateSubjectException;
import lab1.exceptions.MarkOutOfBoundsException;
import lab1.pupils.Pupil;
import lab3.visitor.Visitor;

public class SynchronizedPupil implements Pupil {
    private Pupil pupil;

    public SynchronizedPupil(Pupil pupil) {
        this.pupil = pupil;
    }

    @Override
    public synchronized String getSurname() {
        return pupil.getSurname();
    }

    @Override
    public synchronized void setSurname(String surname) {
        pupil.setSurname(surname);
    }

    @Override
    public synchronized int getMark(int index) {
        return pupil.getMark(index);
    }

    @Override
    public synchronized int[] getMarks() {
        return pupil.getMarks();
    }

    @Override
    public synchronized  void setMark(int index, int mark) throws MarkOutOfBoundsException {
        pupil.setMark(index, mark);
    }

    @Override
    public synchronized String getSubject(int index) {
        return pupil.getSubject(index);
    }

    @Override
    public synchronized String[] getSubjects() {
        return pupil.getSubjects();
    }

    @Override
    public synchronized void setSubject(int index, String newSubject) throws DuplicateSubjectException {
        pupil.setSubject(index, newSubject);
    }

    @Override
    public synchronized int getArrayLength() {
        return pupil.getArrayLength();
    }

    @Override
    public synchronized void addMarkAndSubject(int mark, String subject) throws DuplicateSubjectException, MarkOutOfBoundsException {
        pupil.addMarkAndSubject(mark, subject);
    }

    @Override
    public synchronized void visit(Visitor visitor) {
        pupil.visit(visitor);
    }
}
