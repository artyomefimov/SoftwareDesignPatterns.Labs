package lab3;

import lab1.pupils.Pupil;
import lab1.pupils.Pupils;
import lab1.pupils.Schoolboy;
import lab1.pupils.Student;
import lab3.chainofresponibility.ColumnPupilWriter;
import lab3.chainofresponibility.RawPupilWriter;
import lab3.command.WriteToColumnCommand;
import lab3.command.WriteToRawCommand;
import lab3.strategy.BubbleSort;
import lab3.strategy.QuickSort;
import lab3.visitor.PrintVisitor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Iterator;

public class Lab3 {
    public static void main(String[] args) throws Exception {
        Student petrov = new Student("Petrov", 0);

        System.out.println(petrov.getSurname().toUpperCase());
        petrov.addMarkAndSubject(4, "Programming");
        petrov.addMarkAndSubject(5, "History");

        Pupils.showSubjectsOf(petrov);
        Pupils.showMarksOf(petrov);
        System.out.println("Average marks value = " + Pupils.getAverageMarkOf(petrov));

        System.out.println();

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

        System.out.println("Chain of responsibility");
        RawPupilWriter rawPupilWriter = new RawPupilWriter();
        rawPupilWriter.setNextResponsibleWriter(new ColumnPupilWriter());

        rawPupilWriter.write(petrov);
        rawPupilWriter.write(ivanov);

        System.out.println("\nCommand\n");

        try (BufferedWriter petrovWriter = new BufferedWriter(new FileWriter("src/lab3/command/" + petrov.getSurname() + ".txt"));
             BufferedWriter ivanovWriter = new BufferedWriter(new FileWriter("src/lab3/command/" + ivanov.getSurname() + ".txt"))) {

            petrov.setPrintCommand(new WriteToRawCommand());
            ivanov.setPrintCommand(new WriteToColumnCommand());

            petrov.print(petrovWriter);
            ivanov.print(ivanovWriter);
        }

        System.out.println("\nIterator\n");

        Schoolboy sidorov = new Schoolboy("Sidorov", 2);
        System.out.println(sidorov.getSurname().toUpperCase());
        sidorov.setSubject(0, "Mathematics");
        sidorov.setSubject(1, "Physics");

        sidorov.setMark(0, 4);
        sidorov.setMark(1, 3);

        sidorov.addMarkAndSubject(4, "English");
        sidorov.addMarkAndSubject(5, "History");

        Pupils.showSubjectsOf(sidorov);
        Pupils.showMarksOf(sidorov);
        System.out.println("Average marks value = " + Pupils.getAverageMarkOf(sidorov));

        System.out.println();

        Iterator schoolboyIterator = sidorov.getIterator();
        while (schoolboyIterator.hasNext()) {
            System.out.println(schoolboyIterator.next());
        }

        System.out.println("\nMemento\n");

        System.out.println("Creating memento of this student");
        Pupils.showMarksOf(ivanov);
        Pupils.showSubjectsOf(ivanov);
        ivanov.setMemento();
        ivanov.addMarkAndSubject(3, "TestSubject");
        System.out.println("\nivanov changed");
        Pupils.showMarksOf(ivanov);
        Pupils.showSubjectsOf(ivanov);

        System.out.println("\nivanov from memento");
        Student ivanovMemento = ivanov.getMemento();
        Pupils.showMarksOf(ivanovMemento);
        Pupils.showSubjectsOf(ivanovMemento);

        System.out.println("\nStrategy\n");

        Pupil[] pupils = new Pupil[] {petrov, sidorov, ivanov, ivanovMemento};
        System.out.println(Utils.averageMarksAsString(pupils));

        System.out.println("\nBubble Sort");
        long curTime = System.nanoTime();
        Pupil[] bubble = new BubbleSort().sort(pupils);
        long endTime = System.nanoTime();
        System.out.println(Utils.averageMarksAsString(bubble));
        System.out.println(endTime - curTime + " ns");

        System.out.println("\nQuick Sort");
        curTime = System.nanoTime();
        Pupil[] quick = new QuickSort().sort(pupils);
        endTime = System.nanoTime();
        System.out.println(Utils.averageMarksAsString(quick));
        System.out.println(endTime - curTime + " ns");

        System.out.println("\nVisitor\n");

        PrintVisitor visitor = new PrintVisitor();
        System.out.println("Visiting student Ivanov... Printing to a row");
        ivanov.visit(visitor);

        System.out.println();

        System.out.println("Visiting schoolboy Sidorov... Printing to a column");
        sidorov.visit(visitor);
    }
}
