package mk.ukim.finki.emt.advertisement.ordermanagement.application;

import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.ordermanagement.domain.model.AdId;

import java.util.List;

public interface AdCatalog {

    List<Ad> findAll();

    Ad findById(AdId id);

}
