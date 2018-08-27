package com.example.imageservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    public static String UPLOAD_ROOT = "upload-dir";

    private final ResourceLoader resourceLoader;

    private final ImageRepository imageRepository;


    public ImageService(ResourceLoader resourceLoader,
                        ImageRepository imageRepository) {
        this.resourceLoader = resourceLoader;
        this.imageRepository = imageRepository;
    }

    public Mono<Resource> findOneImage(String id) {
        return Mono.fromSupplier(() ->
                resourceLoader.getResource("file:" + UPLOAD_ROOT + "/" + id));
    }

    public Flux<Image> findAllUsersImages(String authId) {
        return imageRepository.findByAuthID(authId);
    }

    /**
    public Flux<String> createImage(
            Flux<FilePart> files //,
            // String authID
    ) {
        return files
                .flatMap(file -> {
                    String id = UUID.randomUUID().toString();
                    Mono<Image> saveDatabaseImage = imageRepository.save(new Image(id,"bob"));

                    Mono<Void> copyFile = Mono.just(
                            Paths.get(UPLOAD_ROOT, id)
                                    .toFile())
                            .map(destFile -> {
                                try {
                                    destFile.createNewFile();
                                    return destFile;
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .flatMap(file::transferTo).doOnNext(System.out::println);

                    return Mono.when(saveDatabaseImage, copyFile).doOnNext(System.out::println)
                            .map(x -> id)
                            .doOnNext(System.out::println);
                });
    }
    */

    public Mono<String> createImage(
            Mono<FilePart> files //,
            // String authID
    ) {
        String id = UUID.randomUUID().toString();
        return files
                .flatMap(file -> {

                    Mono<Image> saveDatabaseImage = imageRepository.save(new Image(id,"bob"));

                    Mono<Void> copyFile = Mono.just(
                            Paths.get(UPLOAD_ROOT, id)
                                    .toFile())
                            .map(destFile -> {
                                try {
                                    destFile.createNewFile();
                                    return destFile;
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .flatMap(file::transferTo).doOnNext(System.out::println);

                    return Mono.when(saveDatabaseImage, copyFile).doOnNext(System.out::println)
                            .map(x -> id);
                }).thenReturn(id);
    }

    public Mono<Void> deleteImage(String id) {
        Mono<Void> deleteDatabaseImage = imageRepository
                .findById(id)
                .flatMap(imageRepository::delete);

        Mono<Object> deleteFile = Mono.fromRunnable(() -> {
            try {
                Files.deleteIfExists(
                        Paths.get(UPLOAD_ROOT, id));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })
                .log("deleteImage-file");

        return Mono.when(deleteDatabaseImage, deleteFile)
                .log("deleteImage-when")
                .then()
                .log("deleteImage-done");
    }

    @Bean
    CommandLineRunner setUp() throws IOException {
        return (args) -> {
            FileSystemUtils.deleteRecursively(new File(UPLOAD_ROOT));

            Files.createDirectory(Paths.get(UPLOAD_ROOT));

            /*
            FileCopyUtils.copy("Test file",
                    new FileWriter(UPLOAD_ROOT +
                            "/learning-spring-boot-cover.jpg"));

            FileCopyUtils.copy("Test file2",
                    new FileWriter(UPLOAD_ROOT +
                            "/learning-spring-boot-2nd-edition-cover.jpg"));

            FileCopyUtils.copy("Test file3",
                    new FileWriter(UPLOAD_ROOT + "/bazinga.png"));
            */
        };
    }
}
