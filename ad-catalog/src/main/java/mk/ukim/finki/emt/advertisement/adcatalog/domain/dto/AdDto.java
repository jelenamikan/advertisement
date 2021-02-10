package mk.ukim.finki.emt.advertisement.adcatalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.AdId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Type;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Currency;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdDto {

    private String id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String creatorId;

    Set<Type> types;

    private Currency currency;

    private int price;

    private int quantity;

    @NonNull
    private boolean isProduct;

    private String imgUrl;

    List<String> categories;

}
