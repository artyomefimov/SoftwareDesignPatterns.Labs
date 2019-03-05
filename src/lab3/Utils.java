package lab3;

import lab1.pupils.Pupil;
import lab1.pupils.Pupils;

public class Utils {
    public static String averageMarksAsString(Pupil[] pupils) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Pupil pupil : pupils) {
            stringBuilder.append(pupil.getSurname())
                    .append(": ")
                    .append(Pupils.getAverageMarkOf(pupil))
                    .append(" ");
        }
        return stringBuilder.toString();
    }
}
