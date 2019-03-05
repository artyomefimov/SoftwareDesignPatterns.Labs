package lab4.dao;

import lab1.pupils.Pupil;
import lab1.pupils.Pupils;
import lab1.pupils.Schoolboy;
import lab1.pupils.Student;

import java.util.ArrayList;
import java.util.List;

public class DaoRun {
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

        List<Pupil> pupils = new ArrayList<>();
        pupils.add(petrov);
        pupils.add(ivanov);

        System.out.println("Initial pupils: " + pupils);

        System.out.println();

        PupilDAO textPupilDAO = new TextPupilDAO();
        textPupilDAO.write(pupils);

        List<Pupil> readPupils = textPupilDAO.read();
        System.out.println("Read text pupils: " + readPupils);

        System.out.println();

        PupilDAO bytePupilDAO = new BytePupilDAO();
        bytePupilDAO.write(pupils);
        readPupils = bytePupilDAO.read();
        System.out.println("Read byte pupils: " + readPupils);
    }
}
