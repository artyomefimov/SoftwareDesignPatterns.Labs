package lab2.decorator;

import lab1.pupils.Pupil;

public class PupilDecorator {
    public Pupil synchronizedPupil(Pupil pupil) throws IllegalArgumentException {
        if (pupil == null)
            throw new IllegalArgumentException("Pupil is null");
        return new SynchronizedPupil(pupil);
    }
}
