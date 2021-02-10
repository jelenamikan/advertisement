package mk.ukim.finki.emt.advertisement.adcatalog.bootstrap;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.*;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.repository.AdRepository;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.repository.CategoryRepository;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.geo.CityName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

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
    public void load() {
        if (categoryRepository.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {
        Category vozila = new Category(new CategoryId("1"), "Моторни возила");
        Category nedviznosti = new Category(new CategoryId("2"), "Недвижности");
        Category kompjuteri = new Category(new CategoryId("3"), "Компјутери");
        Category mobilni = new Category(new CategoryId("4"), "Мобилни телефони и додатоци");
        Category vrabotuvanje = new Category(new CategoryId("5"), "Вработување");

        Set<Type> types1 = new HashSet<>();
        types1.add(Type.SELLING);
        types1.add(Type.USED);

        Ad voz1 = createAd(new AdId("1"), "Се продава Форд Ескорт",
                "Колата е во добра состојба и е регистрирана.",
                "1",
                new CityName("Битола"),
                types1,
                new Money(Currency.EUR, 700),
                1,
                true,
                "https://i.ytimg.com/vi/kMc9uzM27Jg/maxresdefault.jpg");

        types1.add(Type.EXCHANGE);

        Ad voz2 = createAd(new AdId("2"), "Се продава Мерцедес Е220 Анангард",
                "Продавам Мерцедес Е220 Авангард во извонредна состојба со целосна опрема.",
                "1",
                new CityName("Кочани"),
                types1,
                new Money(Currency.EUR, 1200),
                1,
                true,
                "https://i.ytimg.com/vi/UHAZ5eAaR8c/maxresdefault.jpg");

        Ad savedVoz1 = adRepository.save(voz1);
        Ad savedVoz2 = adRepository.save(voz2);

        vozila.getAds().add(savedVoz1);
        vozila.getAds().add(savedVoz2);
        categoryRepository.save(vozila);

        Set<Type> types2 = new HashSet<>();
        types2.add(Type.SELLING);
        types2.add(Type.NEW);

        Ad ned1 = createAd(new AdId("3"), "Се издава стан во Карпош 4",
                "Станот е трособен со централно греење на улица Вич.",
                "2",
                new CityName("Скопје"),
                types2,
                new Money(Currency.MKD, 10000),
                1,
                false,
                "https://media.pazar3.mk/Image/b94a4db0-4197-4df9-8403-435807de518d/20200927/false/false/1280/960/Stan-vo-Karpos-4.jpeg?noLogo=true");

        Ad ned2 = createAd(new AdId("4"), "Се издава стан во Центар",
                "Станот е еднособен и целосно опремен.",
                "3",
                new CityName("Скопје"),
                types2,
                new Money(Currency.MKD, 15000),
                1,
                false,
                "https://media.pazar3.mk/Image/e9e8e9f582e340e292ce0e6734f9ee93/20201025/false/false/1280/960/Se-iznajmuva-stan-vo-Centar.jpeg?noLogo=true");

        Ad savedNed1 = adRepository.save(ned1);
        Ad savedNed2 = adRepository.save(ned2);

        nedviznosti.getAds().add(savedNed1);
        nedviznosti.getAds().add(savedNed2);
        categoryRepository.save(nedviznosti);

    }

    private Ad createAd(AdId adId, String name, String description, String creatorId, CityName cityName, Set<Type> types,
                        Money price, int qnt, boolean isProduct, String imgUrl) {
        Ad ad = new Ad(adId, name, description, creatorId, cityName, types, price, qnt, isProduct, imgUrl);
        return ad;
    }
}
