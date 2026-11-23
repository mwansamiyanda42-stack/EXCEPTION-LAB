# ExceptionLab – ICT261 Unit 4

Java console importer for the lab topic **Exception Handling and Error Management**.

## Requirements covered
- Checked and unchecked exceptions
- `try`, `catch`, `finally`, `throw`, and `throws`
- Custom `DuplicateStudentException extends RuntimeException`
- Try-with-resources
- Friendly console messages and technical logging
- Validation of two comma-separated fields and marks from 0 to 100
- Duplicate student ID detection
- Skip a bad record and continue processing

## Project files
- `src/MarksImporter.java` – complete Java source code
- `marks.txt` – sample input supplied by the lab
- `ACTIVITY_ANSWERS.md` – Activity A and Activity D answers, plus recovery strategy
- `tests/` – input files for the required test cases
- `ICT261_Unit4_Lab_Sheet.pdf` – copy of the supplied lab sheet (if included)

## Run in VS Code
1. Open the `ExceptionLab` folder in VS Code.
2. Make sure a JDK (Java 11 or newer) is installed.
3. Open **Terminal → New Terminal**.
4. From the `ExceptionLab` folder, compile:

   `javac -d out src/MarksImporter.java`

5. Run the sample file:

   `java -cp out MarksImporter marks.txt`

To test a different input file, pass its path after `MarksImporter`, for example:

`java -cp out MarksImporter tests/T4_out_of_range.txt`

## Required tests
- T1: valid record accepted – `tests/T1_valid.txt`
- T2: non-numeric mark reported and skipped – `tests/T2_non_numeric.txt`
- T3: duplicate student ID reported and skipped – `tests/T3_duplicate.txt`
- T4: out-of-range mark rejected and skipped – `tests/T4_out_of_range.txt`
- T5: missing file – run `java -cp out MarksImporter absent.txt`

For T5, the program should show a friendly file-not-found message and still print `Import attempt finished`.

**Evidence reminder:** Run T1–T5 on your own computer and capture the actual console outputs/screenshots for submission. The expected behavior is described above; this package does not pretend that those screenshots have already been captured.
