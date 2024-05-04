package Uebungen_AD.week11.n41.array.search;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Chat GPT script to find the longest path on a filesystem (not tested yet)
 */
public class FindLongestPath {
    public static void main(String[] args) {
        Path startDir = Paths.get("C:\\"); // Start directory
        AtomicReference<Path> longestPath = new AtomicReference<>();
        AtomicReference<Integer> maxLength = new AtomicReference<>(0);

        try {
            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    int length = file.toAbsolutePath().toString().length();
                    if (length > maxLength.get()) {
                        maxLength.set(length);
                        longestPath.set(file);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    // Ignore directories that can't be accessed
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });

            if (longestPath.get() != null) {
                System.out.println("Longest path: " + longestPath.get());
                System.out.println("Length: " + maxLength.get());
            } else {
                System.out.println("No accessible paths found.");
            }
        } catch (IOException e) {
            System.err.println("Error walking the file system: " + e.getMessage());
        }
    }
}


