import java.io.BufferedReader;j
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarksImporter {
    private static final Logger LOGGER =
            Logger.getLogger(MarksImporter.class.getName());

    // Custom unchecked exception required by the lab.
    static class DuplicateStudentException extends RuntimeException {
        public DuplicateStudentException(String message) {
            super(message);
        }
    }

    static void importMarks(String filename) throws IOException {
        Set<String> seen = new HashSet<>();

        // try-with-resources closes the reader automatically, even if an
        // exception occurs while reading or processing the file.
        try (BufferedReader br =
                     new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                try {
                    String[] parts = line.split(",", -1);

                    // TODO 1: exactly two fields are required.
                    if (parts.length != 2) {
                        throw new IllegalArgumentException(
                                "Expected student ID and mark separated by a comma");
                    }

                    String studentId = parts[0].trim();
                    String markText = parts[1].trim();

                    if (studentId.isEmpty() || markText.isEmpty()) {
                        throw new IllegalArgumentException(
                                "Student ID and mark must not be empty");
                    }

                    // TODO 2: parse the mark and validate the range 0..100.
                    int mark = Integer.parseInt(markText);

                    if (mark < 0 || mark > 100) {
                        throw new IllegalArgumentException(
                                "Mark must be between 0 and 100");
                    }

                    // TODO 3: detect duplicate student IDs.
                    if (seen.contains(studentId)) {
                        throw new DuplicateStudentException(
                                "Duplicate student ID: " + studentId);
                    }
                    seen.add(studentId);

                    // TODO 4: display an accepted record.
                    System.out.println("Accepted: " + studentId + " = " + mark);

                } catch (NumberFormatException e) {
                    LOGGER.log(Level.WARNING,
                            "Invalid numeric mark on line " + lineNumber, e);
                    System.out.println("Line " + lineNumber
                            + ": mark must be a whole number; record skipped.");

                } catch (DuplicateStudentException | IllegalArgumentException e) {
                    LOGGER.log(Level.WARNING,
                            "Invalid record on line " + lineNumber, e);
                    System.out.println("Line " + lineNumber + ": "
                            + e.getMessage() + "; record skipped.");
                }
            }
        }
    }

    public static void main(String[] args) {
        String filename = args.length > 0 ? args[0] : "marks.txt";

        try {
            importMarks(filename);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Marks file could not be found: " + filename, e);
            System.out.println("Marks file not found; check the file name.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An I/O error occurred while importing marks.", e);
            System.out.println("The marks file could not be read. Please check it and try again.");
        } finally {
            System.out.println("Import attempt finished");
        }
    }
}
