package ohtu.data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.domain.User;

/**
 *
 * @author eekuurne
 */
public class FileUserDAO implements UserDao {

    private String fileName;
    
    public FileUserDAO(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<User> listAll() {
        List <User> users = new ArrayList<User>();
        
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                users.add(new User(line.substring(0, line.indexOf(",")), line.substring(line.indexOf(",") + 1, line.length())));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        List <User> users = listAll();
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void add(User user) {
        String line = user.getUsername() + "," + user.getPassword() + "\n";

        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(line);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
