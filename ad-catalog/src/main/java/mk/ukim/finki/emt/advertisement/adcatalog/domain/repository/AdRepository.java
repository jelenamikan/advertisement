package mk.ukim.finki.emt.advertisement.adcatalog.domain.repository;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.AdId;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Type;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.geo.CityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AdRepository extends JpaRepository<Ad, AdId> {

    List<Ad> findAllByDeletedFalse();

    Optional<Ad> findByIdAndDeletedFalse(AdId adId);

    List<Ad> findAllByIsProductAndDeletedFalse(Boolean isProduct);

    List<Ad> findAllByCreatorIdEqualsAndDeletedFalse(String creatorId);

    List<Ad> findAllByCityNameEqualsAndDeletedFalse(CityName cityName);

}
