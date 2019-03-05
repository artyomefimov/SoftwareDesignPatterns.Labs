package lab3.chainofresponibility;

import lab1.pupils.Pupil;

import java.io.IOException;

public interface PupilWriterBySubjectsCount {
    void write(Pupil pupil) throws IOException;
    void setNextResponsibleWriter(PupilWriterBySubjectsCount nextResponsibleWriter);
}
