package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Image;

public interface imageRepository extends JpaRepository<Image,Long> {
}
