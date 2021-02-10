package mk.ukim.finki.emt.advertisement.adcatalog.port.rest;

import mk.ukim.finki.emt.advertisement.adcatalog.application.AdCatalog;
import mk.ukim.finki.emt.advertisement.adcatalog.application.CategoryCatalog;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.dto.AdDto;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.AdId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/ads")
public class AdController {

    private final AdCatalog adCatalog;

    public AdController(AdCatalog adCatalog) {
        this.adCatalog = adCatalog;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ad> getAllAds(){
        return adCatalog.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ad getAdById(@PathVariable AdId id){
        return adCatalog.findById(id);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Ad> getAllProducts(){
        return adCatalog.findProducts();
    }

    @GetMapping("/basic")
    @ResponseStatus(HttpStatus.OK)
    public List<Ad> getAllBasicAds(){
        return adCatalog.findBasicAds();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ad createAd(@RequestBody AdDto adDto){
        return adCatalog.saveAd(adDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAd(@PathVariable AdId id){
        adCatalog.deleteById(id);
    }

}
