package lab3.strategy;

import lab1.pupils.Pupil;
import lab1.pupils.Pupils;

public class BubbleSort implements SortingStrategy {
    @Override
    public Pupil[] sort(Pupil... pupils) {
        for (int i = 0; i < pupils.length - 1; i++) {
            for (int j = 0; j < pupils.length - i - 1; j++) {
                if (Pupils.getAverageMarkOf(pupils[j]) > Pupils.getAverageMarkOf(pupils[j+1])) {
                    Pupil temp = pupils[j];
                    pupils[j] = pupils[j + 1];
                    pupils[j + 1] = temp;
                }
            }
        }
        return pupils;
    }
}
