package mk.ukim.finki.emt.advertisement.adcatalog.application;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.dto.AdDto;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.AdId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Category;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.repository.AdRepository;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.repository.CategoryRepository;
import mk.ukim.finki.emt.advertisement.adcatalog.integration.OrderItemAddedEvent;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.geo.CityName;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AdCatalog {

    private final AdRepository adRepository;
    private final CategoryRepository categoryRepository;

    public AdCatalog(AdRepository adRepository, CategoryRepository categoryRepository) {
        this.adRepository = adRepository;
        this.categoryRepository = categoryRepository;
    }

    @NonNull
    public List<Ad> findAll() {
        return adRepository.findAllByDeletedFalse();
    }

    @NonNull
    public Ad findById(@NonNull AdId adId) {
        Objects.requireNonNull(adId, "adId must not be null");
        return adRepository.findByIdAndDeletedFalse(adId).orElseThrow(() -> new RuntimeException("Ad Not Found!!!"));
    }

    public List<Ad> findByCreatorId(@NonNull String creatorId){
        return adRepository.findAllByCreatorIdEqualsAndDeletedFalse(creatorId);
    }

    public List<Ad> findProducts() {
        return adRepository.findAllByIsProductAndDeletedFalse(true);
    }

    public List<Ad> findBasicAds() {
        return adRepository.findAllByIsProductAndDeletedFalse(false);
    }

    public List<Ad> findAllByCity(CityName cityName) {
        return adRepository.findAllByCityNameEqualsAndDeletedFalse(cityName);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderCreatedEvent(OrderItemAddedEvent event) {
        Ad ad = adRepository.findByIdAndDeletedFalse(event.getAdId()).orElseThrow(RuntimeException::new);
        ad.subtractQuantity(event.getQuantity());
        adRepository.save(ad);
    }

    public Ad saveAd(AdDto adDto) {
        Money money = new Money();
        if (adDto.getCurrency() != null) {
            money = new Money(adDto.getCurrency(), adDto.getPrice());
        }
        CityName cityName = new CityName(adDto.getCityName());
        Ad ad = new Ad(new AdId(), adDto.getTitle(), adDto.getDescription(), adDto.getCreatorId(), cityName,
                adDto.getTypes(), money, adDto.getQuantity(), adDto.isProduct(), adDto.getImgUrl());
        for (String str : adDto.getCategories()) {
            CategoryId categoryId = new CategoryId(str);
            Category category = categoryRepository.findByIdAndDeletedFalse(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
            ad.getCategories().add(category);
        }
        return adRepository.save(ad);
    }

    public void deleteById(AdId adId) {
        adRepository.findById(adId).get().setDeleted(true);
    }

}

