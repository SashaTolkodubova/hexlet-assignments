package exercise;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    private static Path getAbsolutePath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }
    public static CompletableFuture<String> unionFiles(String filePath1, String filePath2, String filePath3) {
        CompletableFuture<String> file1Future = CompletableFuture.supplyAsync(() -> {
            String content;
            try {
                content = Files.readString(getAbsolutePath(filePath1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        CompletableFuture<String> file2Future = CompletableFuture.supplyAsync(() -> {
            String content;
            try {
                content = Files.readString(getAbsolutePath(filePath2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });
        return file1Future.thenCombine(file2Future, (content1, content2) -> {
            String resulContent = content1 + content2;
            try {
                Path path = Files.writeString(getAbsolutePath(filePath3), resulContent, StandardOpenOption.CREATE);
                return Files.readString(path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(ex -> {
            System.out.println("Exception was thrown: " + ex.getMessage());
            return "file3 mistake";
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String directoryPath) throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            Path path = Paths.get(directoryPath);
            try {
                List<Path> files = Files.list(path)
                        .filter(pathToFile -> !Files.isDirectory(pathToFile))
                        .toList();
                long fileSize = 0;
                for (Path file: files) {
                    fileSize += Files.size(file);
                }
                    System.out.println("FileSize = " + fileSize);
                    return fileSize;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        future.get();
        return future;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/file3.txt");
        getDirectorySize("src/main/resources");
        // END
    }
}

