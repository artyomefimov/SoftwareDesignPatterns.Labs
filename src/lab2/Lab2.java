package lab2;

import lab1.pupils.Pupil;
import lab1.pupils.Pupils;
import lab1.pupils.Schoolboy;
import lab1.pupils.Student;
import lab2.adapter.StringWriter;

public class Lab2 {
    public static void main(String[] args) throws Exception {
        Pupil ivanov = new Student("Ivanov", 2);

        System.out.println(ivanov.getSurname().toUpperCase());
        ivanov.setSubject(0, "Mathematics");
        ivanov.setSubject(1, "Physics");

        ivanov.setMark(0, 4);
        ivanov.setMark(1, 3);

        ivanov.addMarkAndSubject(4, "English");
        ivanov.addMarkAndSubject(5, "History");

        Pupils.showSubjectsOf(ivanov);
        Pupils.showMarksOf(ivanov);
        System.out.println("Average marks value = " + Pupils.getAverageMarkOf(ivanov));
        System.out.println();

        Pupil petrov = new Schoolboy("Petrov", 0);

        System.out.println(petrov.getSurname().toUpperCase());
        petrov.addMarkAndSubject(4, "Programming");
        petrov.addMarkAndSubject(5, "History");

        Pupils.showSubjectsOf(petrov);
        Pupils.showMarksOf(petrov);
        System.out.println("Average marks value = " + Pupils.getAverageMarkOf(petrov));

        System.out.println("\nDecorating pupils...");


        Pupil syncIvanov = Pupils.synchronizedPupil(ivanov);
        System.out.println(ivanov.toString());
        System.out.println(syncIvanov.toString() + " " + syncIvanov.getSurname() + "\n");

        Pupil syncPetrov = Pupils.synchronizedPupil(petrov);
        System.out.println(petrov.toString());
        System.out.println(syncPetrov.toString() + " " + syncPetrov.getSurname() + "\n");

        System.out.println("Adapting strings...");

        String[] strings = new String[]{"1", "2", "3", "4", "5"};

        StringWriter writer = new StringWriter();
        writer.writeStrings(strings);

    }
}
