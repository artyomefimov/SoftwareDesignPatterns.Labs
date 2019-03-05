package lab1.factories;

import lab1.pupils.Pupil;
import lab1.pupils.Student;

public class StudentFactory implements PupilFactory {
    @Override
    public Pupil createPupil(String surname, int marksAndSubjectsCount) {
        return new Student(surname, marksAndSubjectsCount);
    }
}
