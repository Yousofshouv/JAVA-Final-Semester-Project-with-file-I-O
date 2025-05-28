 import java.io.*;

public class Department implements Searchable {
    private String name;
    private Student[] students = new Student[10];
    private int count = 0;

    public Department(String name) {
        this.name = name;
        loadFromCSV();
    }

    public String getName() {
        return name;
    }

    public int getRemainingSlots() {
        return 10 - count;
    }

    public boolean addStudent(String name, String id) {
        if (count >= 10) return false;
        students[count++] = new Student(name, id);
        saveToCSV();
        return true;
    }



    public boolean deleteStudent(String input) {
    for (int i = 0; i < count; i++) {
        if (students[i].getName().equalsIgnoreCase(input) || students[i].getId().equalsIgnoreCase(input)) {
            students[i] = students[count - 1]; 
            students[--count] = null;       
            saveToCSV();                     
            return true;
        }
    }
    return false;
}

 

    public String getAllStudents() {
        if (count == 0) return "No students found in " + name + " department.";
        String result = name + " Students:\n";
        for (int i = 0; i < count; i++) {
            result += students[i].displayInfo() + "\n";
        }
        return result;
    }

    @Override
    public String searchByName(String name) {
        for (int i = 0; i < count; i++) {
            if (students[i].getName().equalsIgnoreCase(name)) {
                return students[i].displayInfo();
            }
        }
        return "No student found with name: " + name;
    }

    @Override
    public String searchById(String id) {
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equalsIgnoreCase(id)) {
                return students[i].displayInfo();
            }
        }
        return "No student found with ID: " + id;
    }

    private void saveToCSV() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(name + ".csv"))) {    // using PrintWriter and FileWriter
            for (int i = 0; i < count; i++) {
                pw.println(students[i].getName() + "," + students[i].getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromCSV() {
        File file = new File(name + ".csv");
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {      // using Bufferedreader and FileReader
            String line;
            while ((line = br.readLine()) != null && count < 10) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    students[count++] = new Student(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
