package lab1.pupils;

import lab1.exceptions.DuplicateSubjectException;
import lab1.exceptions.MarkOutOfBoundsException;
import lab3.visitor.Visitor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Schoolboy implements Pupil, Cloneable, Serializable {
    private String surname;
    private Register[] registers;

    public Schoolboy(String surname, int length) {
        this.surname = surname;
        this.registers = new Register[length];
        for (int i = 0; i < registers.length; i++) {
            registers[i] = new Register();
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMark(int index) {
        if (index >= 0 && index < registers.length) {
            Register register = registers[index];
            return register.getMark();
        } else return -1;
    }

    @Override
    public int[] getMarks() {
        int [] marks = new int[registers.length];
        for (int i = 0; i < marks.length; i++) {
            marks[i] = registers[i].mark;
        }
        return marks;
    }

    public void setMark(int index, int newMark) throws MarkOutOfBoundsException {
        if (index >= 0 && index < registers.length) {
            checkMarkValue(newMark);
            registers[index].setMark(newMark);
        } else
            System.out.println("Incorrect index: " + index);
    }

    public String getSubject(int index) {
        if (index >= 0 && index < registers.length)
            return registers[index].getSubject();
        else return null;
    }

    @Override
    public String[] getSubjects() {
        String [] subjects = new String[registers.length];
        for (int i = 0; i < subjects.length; i++) {
            subjects[i] = registers[i].subject;
        }
        return subjects;
    }

    public void setSubject(int index, String newSubject) throws DuplicateSubjectException {
        if (newSubject != null && (index >= 0 && index < registers.length)) {
            checkSubjectUniqueness(newSubject);
            registers[index].setSubject(newSubject);
        } else {
            System.out.println("Incorrect parameters");
        }
    }

    public int getArrayLength() {
        return registers.length;
    }

    public void addMarkAndSubject(int mark, String subject) throws DuplicateSubjectException, MarkOutOfBoundsException {
        if (mark < 2 || mark > 5) {
            throw new MarkOutOfBoundsException(mark);
        }
        for (int j = 0; j < registers.length; j++) {
            if (registers[j].getSubject().equals(subject)) {
                throw new DuplicateSubjectException(subject);
            }
        }
        registers = Arrays.copyOf(registers, registers.length + 1);
        for (int i = 0; i < registers.length; i++) {
            if (registers[i] == null) {
                registers[i] = new Register(mark, subject);
            }
        }
    }

    public Iterator getIterator() {
        return new SchoolboyIterator();
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visit(this);
    }

    private void checkSubjectUniqueness(String newSubject) throws DuplicateSubjectException {
        for (Register register : registers) {
            if (register.getSubject().equals(newSubject)) {
                throw new DuplicateSubjectException(newSubject);
            }
        }
    }

    private void checkMarkValue(int mark) throws MarkOutOfBoundsException {
        if (mark < 2 || mark > 5)
            throw new MarkOutOfBoundsException(mark);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Schoolboy newSchoolboy = (Schoolboy) super.clone();
        newSchoolboy.registers = this.registers.clone();

        for (int i = 0; i < this.registers.length; i++) {
            newSchoolboy.registers[i] = (Register) this.registers[i].clone();
        }
        return newSchoolboy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schoolboy schoolboy = (Schoolboy) o;
        return Objects.equals(surname, schoolboy.surname) &&
                Arrays.equals(registers, schoolboy.registers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(surname);
        result = 31 * result + Arrays.hashCode(registers);
        return result;
    }

    @Override
    public String toString() {
        return "Schoolboy{" +
                "surname='" + surname + '\'' +
                ", registers=" + Arrays.toString(registers) +
                '}';
    }

    static class Register implements Cloneable, Serializable {
        private int mark;
        private String subject;

        Register() {
            mark = 0;
            subject = "";
        }

        Register(int mark, String subject) {
            this.mark = mark;
            this.subject = subject;
        }

        int getMark() {
            return mark;
        }

        void setMark(int newMark) {
            mark = newMark;
        }

        String getSubject() {
            return subject;
        }

        void setSubject(String newSubject) {
            subject = newSubject;
        }

        @Override
        public String toString() {
            return "Register{" +
                    "mark=" + mark +
                    ", subject='" + subject + '\'' +
                    '}';
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Register newRegister = (Register) super.clone();
            newRegister.subject = this.subject;
            newRegister.mark = this.mark;
            return newRegister;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Register register = (Register) o;
            return mark == register.mark &&
                    Objects.equals(subject, register.subject);
        }

        @Override
        public int hashCode() {
            return Objects.hash(mark, subject);
        }
    }

    private class SchoolboyIterator implements Iterator {
        private int index;

        @Override
        public boolean hasNext() {
            return index < registers.length;
        }

        @Override
        public Object next() {
            return hasNext() ? registers[index++] : null;
        }
    }
}
