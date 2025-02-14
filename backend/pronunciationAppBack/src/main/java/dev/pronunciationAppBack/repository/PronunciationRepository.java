package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.Pronunciation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PronunciationRepository extends JpaRepository<Pronunciation, String> {
}
