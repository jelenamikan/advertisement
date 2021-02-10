package mk.ukim.finki.emt.advertisement.adcatalog.domain.repository;

import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.Ad;
import mk.ukim.finki.emt.advertisement.adcatalog.domain.model.AdId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdRepository extends JpaRepository<Ad, AdId> {
}