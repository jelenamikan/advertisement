package mk.ukim.finki.emt.advertisement.ordermanagement.application.form;

import lombok.Data;
import lombok.NonNull;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.geo.CityName;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.geo.Country;

import java.io.Serializable;

@Data
public class RecipientAddressForm implements Serializable {

    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private CityName city;
    @NonNull
    private Country country;

    public RecipientAddressForm(){

    }

}
