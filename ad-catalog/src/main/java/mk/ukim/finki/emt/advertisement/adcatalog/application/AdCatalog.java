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
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        return adRepository.findAll();
    }

    @NonNull
    public Ad findById(@NonNull AdId adId) {
        Objects.requireNonNull(adId, "adId must not be null");
        return adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onOrderCreatedEvent(OrderItemAddedEvent event) {
        Ad ad = adRepository.findById(event.getAdId()).orElseThrow(RuntimeException::new);
        ad.subtractQuantity(event.getQuantity());
        adRepository.save(ad);
    }

    public Ad saveAd(AdDto adDto) {
        Money money = new Money();
        if (adDto.getCurrency() != null){
            money = new Money(adDto.getCurrency(), adDto.getPrice());
        }
        Ad ad = new Ad(new AdId(), adDto.getTitle(), adDto.getDescription(), money, adDto.getQuantity(), adDto.isProduct());
        for(String str: adDto.getCategories()){
            CategoryId categoryId = new CategoryId(str);
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
            ad.getCategories().add(category);
        }
        return adRepository.save(ad);
    }

    public void deleteById(AdId adId) {
        adRepository.deleteById(adId);
    }

}

