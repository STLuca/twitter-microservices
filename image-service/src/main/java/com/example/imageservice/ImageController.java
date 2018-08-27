package com.example.imageservice;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.security.Principal;

@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "images/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Mono<ResponseEntity<?>> oneRawImage(@PathVariable String id) {
        return imageService.findOneImage(id)
                .map(resource -> {
                    try {
                        return ResponseEntity.ok()
                                .contentLength(resource.contentLength())
                                .body(new InputStreamResource(
                                        resource.getInputStream()));
                    } catch (IOException e) {
                        return ResponseEntity.badRequest()
                                .body("Couldn't find " + id +
                                        " => " + e.getMessage());
                    }
                });
    }

    @PostMapping(value = "images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> createImage(
            @RequestPart("image") Mono<FilePart> files
    ) {
        return imageService.createImage(files).doOnNext(System.out::println);
    }

    @DeleteMapping("images/{id}")
    public Mono<Void> deleteFile(@PathVariable String id) {
        return imageService.deleteImage(id);
    }

}
