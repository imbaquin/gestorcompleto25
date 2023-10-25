package util;

import model.User;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Task;

public class FileUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void writeToFile(String data, String filepath) throws IOException {
        File directory = new File(filepath).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true))) {
            writer.write(data + System.lineSeparator());
        }
    }

    public static List<User> readFromFile(String filepath) throws IOException {
        List<User> users = new ArrayList<>();
        File file = new File(filepath);
        if (!file.exists()) {
            return users;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                users.add(new User(data[0], data[1], data[2]));
            }
        }
        return users;
    }

    public static void writeTasksToFile(User user, String filepath) throws IOException {
        File directory = new File(filepath).getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, false))) {
            for (int i = 0; i < user.getTasks().size(); i++) {
                Task task = user.getTasks().get(i);
                String data = user.getCedula() + ","
                        + task.getId() + ","
                        + task.getTitle() + ","
                        + task.getDescription() + ","
                        + DATE_FORMAT.format(task.getDueDate());
                writer.write(data + System.lineSeparator());
            }
        }
    }

    public static void readTasksFromFile(User user, String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(user.getCedula())) {
                    int id = Integer.parseInt(data[1]);
                    String title = data[2];
                    String description = data[3];
                    Date dueDate = DATE_FORMAT.parse(data[4]); // Puede lanzar una ParseException, manejar adecuadamente
                    Task task = new Task(id, title, description, dueDate);
                    user.getTasks().add(task);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Es mejor manejar esta excepción de manera más específica
        }
    }
}
