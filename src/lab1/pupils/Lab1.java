package lab1.pupils;

import lab1.factories.SchoolboyFactory;
import lab1.singleton.PropertiesReader;

public class Lab1 {
    public static void main(String[] args) throws Exception {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        System.out.println("singleton hash: " + propertiesReader.hashCode());

        propertiesReader = PropertiesReader.getInstance();
        System.out.println("singleton hash: " + propertiesReader.hashCode());

        propertiesReader = PropertiesReader.getInstance();
        System.out.println("singleton hash: " + propertiesReader.hashCode());

        System.out.println(propertiesReader.getAllValues() + "\n");

        Student ivanov = new Student("Ivanov", 2);

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

        Schoolboy petrov = new Schoolboy("Petrov", 0);

        System.out.println(petrov.getSurname().toUpperCase());
        petrov.addMarkAndSubject(4, "Programming");
        petrov.addMarkAndSubject(5, "History");

        Pupils.showSubjectsOf(petrov);
        Pupils.showMarksOf(petrov);
        System.out.println("Average marks value = " + Pupils.getAverageMarkOf(petrov));

        System.out.println("\nFactory class name: " + Pupils.getFactoryClassName());

        Pupil sidorov = Pupils.createInstance("Sidorov", 2);
        System.out.println(sidorov.getSurname().toUpperCase());
        System.out.println("Before");
        Pupils.showMarksOf(sidorov);
        Pupils.showSubjectsOf(sidorov);

        sidorov.setSubject(0, "OOP");
        sidorov.setMark(0, 3);
        sidorov.setSubject(1, "Assembler");
        sidorov.setMark(1, 3);

        System.out.println("After");
        Pupils.showMarksOf(sidorov);
        Pupils.showSubjectsOf(sidorov);
        System.out.println("Average marks value = " + Pupils.getAverageMarkOf(petrov));

        Pupils.setFactory(new SchoolboyFactory());
        System.out.println("\nFactory changed. Factory class name: " + Pupils.getFactoryClassName());

        Pupil dmitriev = Pupils.createInstance("Dmitriev", 2);
        System.out.println(dmitriev.getSurname().toUpperCase());
        System.out.println("Before");
        Pupils.showMarksOf(dmitriev);
        Pupils.showSubjectsOf(dmitriev);

        dmitriev.setSubject(0, "BD");
        dmitriev.setMark(0, 5);
        dmitriev.setSubject(1, "Matan");
        dmitriev.setMark(1, 3);

        System.out.println("After");

        Pupils.showMarksOf(dmitriev);
        Pupils.showSubjectsOf(dmitriev);
        System.out.println("Average marks value = " + Pupils.getAverageMarkOf(dmitriev));

        System.out.println("\nCreating the clone of student Ivanov...");
        Pupil ivanovClone = (Student)ivanov.clone();
        ivanov.setMark(0, 3);
        System.out.println("Ivanov: " + ivanov.toString());
        System.out.println("Clone: " + ivanovClone.toString());
        System.out.println("ivanov and clone are equal: " + ivanov.equals(ivanovClone));

        System.out.println("\nCreating the clone of schoolboy Petrov...");
        Pupil petrovClone = (Schoolboy)petrov.clone();
        petrov.setMark(0, 3);
        System.out.println("Petrov: " + petrov.toString());
        System.out.println("Clone: " + petrovClone.toString());
        System.out.println("petrov and clone are equal: " + petrov.equals(petrovClone));
    }
}
