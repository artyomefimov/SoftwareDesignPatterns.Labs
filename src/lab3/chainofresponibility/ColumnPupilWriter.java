package lab3.chainofresponibility;

import lab1.pupils.Pupil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ColumnPupilWriter implements PupilWriterBySubjectsCount {
    @Override
    public void write(Pupil pupil) throws IOException {
        if (pupil.getArrayLength() > 3) {
            System.out.println(pupil.getSurname() + " has " + pupil.getArrayLength() + " subjects. Writing in a column");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/lab3/chainofresponibility/" + pupil.getSurname() + ".txt"))) {
                StringBuilder stringBuilder = new StringBuilder(pupil.getSurname())
                        .append("\n")
                        .append(Arrays.toString(pupil.getMarks()))
                        .append("\n")
                        .append(Arrays.toString(pupil.getSubjects()))
                        .append("\n");
                bw.write(stringBuilder.toString());
            }
        } else
            throw new IllegalArgumentException("Can not write pupil: " + pupil);
    }

    @Override
    public void setNextResponsibleWriter(PupilWriterBySubjectsCount nextResponsibleWriter) {
        throw new UnsupportedOperationException();
    }
}
