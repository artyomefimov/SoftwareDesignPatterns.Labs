package lab1.pupils;

import lab1.factories.PupilFactory;
import lab1.factories.StudentFactory;
import lab2.decorator.PupilDecorator;

public class Pupils
{
    private static PupilFactory factory = new StudentFactory();
    private static PupilDecorator decorator = new PupilDecorator();

    public static void showSubjectsOf(Pupil pupil)
    {
        for (int i = 0; i < pupil.getArrayLength(); i++)
        {
            System.out.print(pupil.getSubject(i) + " ");
        }
        System.out.println();
    }

    public static void showMarksOf(Pupil pupil)
    {
        for (int i = 0; i < pupil.getArrayLength(); i++)
        {
            System.out.print(pupil.getMark(i) + " ");
        }
        System.out.println();
    }

    public static double getAverageMarkOf(Pupil pupil)
    {
        double sum = 0;
        for (int i = 0; i < pupil.getArrayLength(); i++)
        {
            sum +=pupil.getMark(i);
        }
        return sum/pupil.getArrayLength();
    }

    public static Pupil createInstance(String name, int marksAndSubjectsCount) {
        return factory.createPupil(name, marksAndSubjectsCount);
    }

    public static String getFactoryClassName() {
        return factory.getClass().getName();
    }

    public static void setFactory(PupilFactory factory) {
        Pupils.factory = factory;
    }

    public static Pupil synchronizedPupil(Pupil pupil) throws IllegalArgumentException {
        return decorator.synchronizedPupil(pupil);
    }
}
