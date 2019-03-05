package lab1.pupils;

import lab1.exceptions.DuplicateSubjectException;
import lab1.exceptions.MarkOutOfBoundsException;
import lab3.command.WriteCommand;
import lab3.visitor.Visitor;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Student implements Pupil, Cloneable, Serializable {
    private String surname;
    private int[] marks;
    private String[] subjects;
    private WriteCommand writeCommand;

    public Student(String surname, int length) {
        this.surname = surname;
        this.marks = new int[length];
        this.subjects = new String[length];

        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = "";
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMark(int index) {
        if (index >= 0 && index < subjects.length)
            return marks[index];
        else return -1;
    }

    @Override
    public int[] getMarks() {
        return marks;
    }

    public void setMark(int index, int newMark) throws MarkOutOfBoundsException {
        if (index >= 0 && index < subjects.length) {
            checkMarkValue(newMark);
            marks[index] = newMark;
        } else {
            System.out.println("Incorrect index: " + index);
        }
    }

    public String getSubject(int index) {
        if (index >= 0 && index < subjects.length)
            return subjects[index];
        else return null;
    }

    @Override
    public String[] getSubjects() {
        return subjects;
    }

    public void setSubject(int index, String newSubject) throws DuplicateSubjectException {
        if (newSubject != null && (index >= 0 && index < subjects.length)) {
            checkSubjectUniqueness(newSubject);
            subjects[index] = newSubject;
        } else {
            System.out.println("Incorrect parameters");
        }
    }

    public void addMarkAndSubject(int mark, String subject) throws DuplicateSubjectException, MarkOutOfBoundsException {
        if (subject != null) {
            checkMarkValue(mark);
            checkSubjectUniqueness(subject);

            marks = Arrays.copyOf(marks, marks.length + 1);
            subjects = Arrays.copyOf(subjects, subjects.length + 1);

            subjects[subjects.length - 1] = subject;
            marks[marks.length - 1] = mark;
        } else {
            System.out.println("Incorrect parameters. Mark:" + mark + ", subject:" + subject);
        }
    }

    public int getArrayLength() {
        return subjects.length;
    }

    public void print(Writer writer) throws IOException {
        writeCommand.write(this, writer);
    }

    public void setPrintCommand(WriteCommand writeCommand) {
        this.writeCommand = writeCommand;
    }

    public Student getMemento() throws IOException, ClassNotFoundException {
        return StudentMemento.getStudent();
    }

    public void setMemento() throws IOException {
        StudentMemento.setStudent(this);
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visit(this);
    }

    private void checkMarkValue(int mark) throws MarkOutOfBoundsException {
        if (mark < 2 || mark > 5)
            throw new MarkOutOfBoundsException(mark);
    }

    private void checkSubjectUniqueness(String newSubject) throws DuplicateSubjectException {
        for (String subject : subjects) {
            if (subject.equals(newSubject)) {
                throw new DuplicateSubjectException(newSubject);
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student newStudent = (Student) super.clone();
        newStudent.marks = this.marks.clone();
        newStudent.subjects = this.subjects.clone();
        newStudent.writeCommand = this.writeCommand;
        return newStudent;
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", marks=" + Arrays.toString(marks) +
                ", subjects=" + Arrays.toString(subjects) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(surname, student.surname) &&
                Arrays.equals(marks, student.marks) &&
                Arrays.equals(subjects, student.subjects) &&
                Objects.equals(writeCommand, student.writeCommand);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(surname, writeCommand);
        result = 31 * result + Arrays.hashCode(marks);
        result = 31 * result + Arrays.hashCode(subjects);
        return result;
    }

    public static class StudentMemento {
        private static byte[] byteMemento;

        public static void setStudent(Student student) throws IOException {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(student);
                byteMemento = bos.toByteArray();
            }
        }

        public static Student getStudent() throws IOException, ClassNotFoundException {
            Student student;
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(byteMemento))) {
                student = ((Student) ois.readObject());
            }
            return student;
        }
    }
}
