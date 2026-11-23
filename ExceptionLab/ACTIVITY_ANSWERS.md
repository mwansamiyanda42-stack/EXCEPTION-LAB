# ICT261 Unit 4 – Activity Answers

## Activity A: Predict and explain

1. `Integer.parseInt("ABC")`
   - Exception: `NumberFormatException`.
   - Classification: unchecked (a subclass of `RuntimeException`).
   - Handling: validate the text or catch `NumberFormatException` around the conversion.

2. `String name = null; name.length();`
   - Exception: `NullPointerException`.
   - Classification: unchecked.
   - Handling: check that `name != null` before calling `length()`, or use a suitable null-safe design.

3. `new java.io.FileReader("missing.txt")`
   - Exception: `FileNotFoundException`.
   - Classification: checked.
   - Handling: catch it or declare it with `throws`, and show a useful message if the file is missing.

**Why not normally catch `OutOfMemoryError` just to continue?**
An `OutOfMemoryError` means the JVM cannot allocate the memory it needs. The application may be in an unstable state, so simply catching the error does not reliably restore the resources needed to continue safely.

## Activity D: Engineer’s review

1. `FileNotFoundException` and `IOException` are checked exceptions. `throws IOException` appears in the `importMarks` method because file reading can fail and the caller (`main`) handles that failure.
2. `throw` actually throws one exception object, for example `throw new IllegalArgumentException("Mark must be between 0 and 100");`. `throws` declares possible exceptions in a method signature, for example `static void importMarks(String filename) throws IOException`.
3. Users need clear messages that tell them what they can do next. The logger keeps technical details useful for debugging without overwhelming ordinary users.
4. An empty `catch` block silently hides a problem and makes debugging difficult. Catching `Exception` before `FileNotFoundException` makes the more specific catch unreachable because the broader catch already catches it.

## Recovery strategy

This solution **skips an invalid record and continues importing the remaining records**. Each line is processed inside its own `try`/`catch`, so a bad number, invalid mark, malformed row, or duplicate ID is reported without stopping the whole file. Only file-level I/O problems leave the import method and are handled in `main`. A student ID is added to the `seen` set only after its mark has been validated, so rejected records are not treated as accepted records.
