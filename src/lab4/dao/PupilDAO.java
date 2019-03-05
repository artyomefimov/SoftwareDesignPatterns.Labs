package lab4.dao;

import lab1.pupils.Pupil;

import java.io.IOException;
import java.util.List;

public interface PupilDAO {
    String TEXT_FILE_PATH = "src/lab4/dao/textPupil.txt";
    String BYTE_FILE_PATH = "src/lab4/dao/bytePupil.txt";
    String STUDENT_CLASSNAME = "Student";
    String SCHOOLBOY_CLASSNAME = "Schoolboy";

    void write(List<Pupil> pupils) throws IOException;
    List<Pupil> read() throws IOException;
}
