package mk.ukim.finki.emt.advertisement.adcatalog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.AdId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.CategoryId;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.financial.Currency;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdDto {

    private String id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    private Currency currency;

    private int price;

    private int quantity;

    @NonNull
    private boolean isProduct;

    List<String> categories;

}
