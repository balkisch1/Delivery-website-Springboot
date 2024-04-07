package com.balkis.delivery.controllers;
import com.balkis.delivery.models.Gallery;
import com.balkis.delivery.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping
    public List<Gallery> getAllGallery() {
        return galleryService.getAllGallery();
    }

    @GetMapping("/{id}")
    public Optional<Gallery> getGalleryById(@PathVariable Long id) {
        return galleryService.getGalleryById(id);
    }

    @PostMapping
    public Gallery saveGallery(@RequestBody Gallery gallery) {
        return galleryService.saveGallery(gallery);
    }

    @DeleteMapping("/{id}")
    public void deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
    }
}
