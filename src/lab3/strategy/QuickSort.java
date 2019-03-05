package lab3.strategy;

import lab1.pupils.Pupil;
import lab1.pupils.Pupils;

import static lab1.pupils.Pupils.getAverageMarkOf;

public class QuickSort implements SortingStrategy {
    private Pupil[] pupils;
    @Override
    public Pupil[] sort(Pupil... pupils) {
        this.pupils = pupils;
        int startIndex = 0;
        int endIndex = pupils.length - 1;
        doSort(startIndex, endIndex);
        return this.pupils;
    }

    private void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (getAverageMarkOf(pupils[i]) <= getAverageMarkOf(pupils[cur]))) {
                i++;
            }
            while (j > cur && (getAverageMarkOf(pupils[cur]) <= getAverageMarkOf(pupils[j]))) {
                j--;
            }
            if (i < j) {
                Pupil temp = pupils[i];
                pupils[i] = pupils[j];
                pupils[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }
}
