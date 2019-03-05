package lab3.chainofresponibility;

import lab1.pupils.Pupil;

import java.io.*;
import java.util.Arrays;

public class RawPupilWriter implements PupilWriterBySubjectsCount {
    private PupilWriterBySubjectsCount next;

    @Override
    public void write(Pupil pupil) throws IOException {
        if (pupil.getArrayLength() <= 3) {
            System.out.println(pupil.getSurname() + " has " + pupil.getArrayLength() + " subjects. Writing in a row");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/lab3/chainofresponibility/" + pupil.getSurname() + ".txt"))) {
                StringBuilder stringBuilder = new StringBuilder(pupil.getSurname())
                        .append(" ")
                        .append(Arrays.toString(pupil.getMarks()))
                        .append(" ")
                        .append(Arrays.toString(pupil.getSubjects()))
                        .append(" ");
                bw.write(stringBuilder.toString());
            }
        } else {
            if (next == null)
                throw new IllegalArgumentException("Can not write pupil: " + pupil);
            else {
                System.out.print(pupil.getSurname() + " has " + pupil.getArrayLength() + " subjects. ");
                System.out.println("Delegating writing to " + next.getClass().getSimpleName());
                next.write(pupil);
            }
        }
    }

    @Override
    public void setNextResponsibleWriter(PupilWriterBySubjectsCount nextResponsibleWriter) {
        next = nextResponsibleWriter;
    }
}
