package com.balkis.delivery.services;
import com.balkis.delivery.models.Gallery;
import com.balkis.delivery.repository.GalleryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class GalleryService{

    private final GalleryRepository galleryrepository;

    public GalleryService(GalleryRepository galleryrepository) {
        this.galleryrepository = galleryrepository;
    }

    public List<Gallery> getAllGallery() {
        return (List<Gallery>) galleryrepository.findAll();
    }

    public Optional<Gallery> getGalleryById(Long id) {
        return galleryrepository.findById(id);
    }

    public Gallery saveGallery(Gallery client) {
        return galleryrepository.save(client);
    }

    public void deleteGallery(Long id) {
        galleryrepository.deleteById(id);
    }




}
