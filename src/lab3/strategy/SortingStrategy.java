package lab3.strategy;

import lab1.pupils.Pupil;

public interface SortingStrategy {
    Pupil[] sort(Pupil... pupils);
}
