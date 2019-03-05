package lab1.factories;

import lab1.pupils.Pupil;

public interface PupilFactory {
    Pupil createPupil(String surname, int marksAndSubjectsCount);
}
