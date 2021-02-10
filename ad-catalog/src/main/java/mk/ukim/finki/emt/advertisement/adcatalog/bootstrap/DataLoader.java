package mk.ukim.finki.emt.advertisement.adcatalog.bootstrap;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.AdId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Category;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.repository.AdRepository;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.repository.CategoryRepository;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class DataLoader {

    private final CategoryRepository categoryRepository;

    private final AdRepository adRepository;

    public DataLoader(CategoryRepository categoryRepository, AdRepository adRepository) {
        this.categoryRepository = categoryRepository;
        this.adRepository = adRepository;
    }

    @PostConstruct
    @Transactional
    public void load(){
        if(categoryRepository.findAll().size() == 0){
            loadData();
        }
    }

    private void loadData(){
        Category vozila = new Category(new CategoryId("1"), "Моторни возила");
        Category nedviznosti = new Category(new CategoryId("2"), "Недвижности");
        Category kompjuteri = new Category(new CategoryId("3"), "Компјутери");
        Category mobilni = new Category(new CategoryId("4"), "Мобилни телефони и додатоци");
        Category vrabotuvanje = new Category(new CategoryId("5"), "Вработување");

        Ad voz1 = createAd(new AdId("1"),"Се продава Форд Ескорт", "Колата е во добра состојба и е регистрирана.",
                new Money(Currency.EUR, 700), 1, true);
        Ad voz2 = createAd(new AdId("2"), "Се продава Мерцедес Е220 Анангард", "Продавам Мерцедес Е220 Авангард во извонредна состојба со целосна опрема.",
                new Money(Currency.EUR, 1200), 1, true);

        Ad savedVoz1 = adRepository.save(voz1);
        Ad savedVoz2 = adRepository.save(voz2);

        vozila.getAds().add(savedVoz1);
        vozila.getAds().add(savedVoz2);
        categoryRepository.save(vozila);

        Ad ned1 = createAd(new AdId("3"), "Се издава стан во Карпош 4", "Станот е трособен со централно греење на улица Вич.",
                new Money(Currency.MKD, 10000), 1, false);

        Ad ned2 = createAd(new AdId("4"), "Се издава стан во Центар", "Станот е еднособен и целосно опремен.",
                new Money(Currency.MKD, 15000), 1, false);

        Ad savedNed1 = adRepository.save(ned1);
        Ad savedNed2 = adRepository.save(ned2);

        nedviznosti.getAds().add(savedNed1);
        nedviznosti.getAds().add(savedNed2);
        categoryRepository.save(nedviznosti);

    }

    private Ad createAd(AdId adId, String name, String descritpion, Money price, int qnt, boolean isProduct) {
        Ad ad = new Ad(adId, name, descritpion, price, qnt, isProduct);
        return ad;
    }
}
