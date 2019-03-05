package lab4.dao;

import lab1.pupils.Pupil;
import lab1.pupils.Schoolboy;
import lab1.pupils.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class TextPupilDAO implements PupilDAO {
    @Override
    public void write(List<Pupil> pupils) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE_PATH))) {
            StringBuilder stringBuilder;
            for (Pupil pupil : pupils) {
                stringBuilder = new StringBuilder(pupil.getClass().getSimpleName())
                        .append(";")
                        .append(pupil.getSurname())
                        .append(";")
                        .append(pupil.getArrayLength())
                        .append(";")
                        .append(Arrays.toString(pupil.getMarks()))
                        .append(";")
                        .append(Arrays.toString(pupil.getSubjects()))
                        .append(";");
                writer.write(stringBuilder.toString());
                writer.newLine();
            }
            System.out.println("Pupils were written in text file\n");
        } catch (Exception e) {
            throw new IOException("Could not write pupil", e);
        }
    }

    @Override
    public List<Pupil> read() throws IOException {
        List<Pupil> pupils = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE_PATH))) {
            String nextLine;
            StringTokenizer tokenizer;
            StringTokenizer marksTokenizer;
            StringTokenizer subjectsTokenizer;
            Pupil pupil;
            String className;
            String surname;
            int marksCount;
            String marks;
            String subjects;
            while ((nextLine = reader.readLine()) != null) {
                tokenizer = new StringTokenizer(nextLine, ";");
                className = tokenizer.nextToken();
                surname = tokenizer.nextToken();
                marksCount = Integer.valueOf(tokenizer.nextToken());

                if (STUDENT_CLASSNAME.equals(className))
                    pupil = new Student(surname, marksCount);
                else if (SCHOOLBOY_CLASSNAME.equals(className))
                    pupil = new Schoolboy(surname, marksCount);
                else
                    throw new IOException("Unknown class of pupil: " + className);

                marks = tokenizer.nextToken();
                StringBuilder builder = new StringBuilder(marks);
                builder.deleteCharAt(0);
                builder.deleteCharAt(builder.length() - 1);
                int i = 0;
                marksTokenizer = new StringTokenizer(builder.toString(), ", ");
                while (marksTokenizer.hasMoreTokens()) {
                    int mark = Integer.valueOf(marksTokenizer.nextToken());
                    pupil.setMark(i, mark);
                    i++;
                }

                subjects = tokenizer.nextToken();
                builder = new StringBuilder(subjects);
                builder.deleteCharAt(0);
                builder.deleteCharAt(builder.length() - 1);
                i = 0;
                subjectsTokenizer = new StringTokenizer(builder.toString(), ", ");
                while (subjectsTokenizer.hasMoreTokens()) {
                    String subject = subjectsTokenizer.nextToken();
                    pupil.setSubject(i, subject);
                    i++;
                }
                System.out.println("read pupil: " + pupil);
                pupils.add(pupil);
            }
        } catch (Exception e) {
            throw new IOException("Could not read pupil", e);
        }
        return pupils;
    }
}
