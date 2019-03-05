package lab4.dao;

import lab1.pupils.Pupil;

import java.io.*;
import java.util.List;

public class BytePupilDAO implements PupilDAO {

    @Override
    public void write(List<Pupil> pupils) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(BYTE_FILE_PATH))) {
            outputStream.writeObject(pupils);
            System.out.println("Pupils were written in byte file\n");
        } catch (Exception e) {
            throw new IOException("Could not write pupil", e);
        }
    }

    @Override
    public List<Pupil> read() throws IOException {
        List<Pupil> pupils;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(BYTE_FILE_PATH))) {
            pupils = ((List<Pupil>) inputStream.readObject());
        } catch (Exception e) {
            throw new IOException("Could not read pupil", e);
        }
        return pupils;
    }
}
