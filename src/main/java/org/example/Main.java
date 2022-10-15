package org.example;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\file.txt");
        Path path1 = Path.of("Users\\file.txt");
        Path path2 = Path.of("./tmp/file");

        Path dir = Path.of("dir");
        try {
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Files.copy(path, path1, StandardCopyOption.REPLACE_EXISTING);

        Files.walkFileTree(dir, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (dir.endsWith("d")) {
                    return FileVisitResult.CONTINUE;
                } else {
                    return FileVisitResult.TERMINATE;
                }
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.TERMINATE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return null;
            }
        });

        ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 10);
        Path path3 = Path.of("file.txt");
        FileChannel fileChannel = FileChannel.open(path3);
        int read = fileChannel.read(buffer);

    }
}