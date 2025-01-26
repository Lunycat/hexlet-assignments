package exercise;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

class App {

    public static CompletableFuture<String> unionFiles(String filePath1, String filePath2, String dest) throws Exception {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> readFile(filePath1));
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> readFile(filePath2));
        Path destPath = Paths.get(dest);
        CompletableFuture<String> result = cf1.thenCombine(cf2, (body1, body2) -> body1 + body2);
        Files.writeString(destPath, result.get());

        return result;
    }

    public static void main(String[] args) throws Exception {
        String filePath1 = "src/main/resources/file1.txt";
        String filePath2 = "src/main/resources/file2.txt";
        String dest = "src/main/resources/dest.txt";
        unionFiles(filePath1, filePath2, dest);
    }

    private static String readFile(String filePath) {
        Path path = Paths.get(filePath).normalize().toAbsolutePath();
        String result = null;
        try {
            result = Files.readString(path, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.out.println("NoSuchFileException");
        }
        return result;
    }
}

