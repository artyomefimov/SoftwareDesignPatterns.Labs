package lab1.factories;

import lab1.pupils.Pupil;
import lab1.pupils.Schoolboy;

public class SchoolboyFactory implements PupilFactory {
    @Override
    public Pupil createPupil(String surname, int marksAndSubjectsCount) {
        return new Schoolboy(surname, marksAndSubjectsCount);
    }
}
