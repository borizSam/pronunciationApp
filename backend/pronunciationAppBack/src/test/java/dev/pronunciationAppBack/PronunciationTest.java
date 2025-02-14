package dev.pronunciationAppBack;

import dev.pronunciationAppBack.repository.PronunciationRepository;
import dev.pronunciationAppBack.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.pronunciationAppBack.model.Word;
import dev.pronunciationAppBack.model.Pronunciation;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PronunciationTest {

    @Autowired
    WordRepository wordRepository;
    @Autowired
    PronunciationRepository pronunciationRepository;

    @Test
    public void AssignTestWord(){

        //Word w1 = new Word();

        Pronunciation p1 = new Pronunciation();
        p1.setId("1");
        p1.setAudioDescription("test");
        p1.setAudioDuration(1);
        p1.setAudioSize(1);
        p1.setAudioUrl("test");
        p1.setDefinition("test");
        p1.setPhoneticSpelling("test");
        p1.setSpeakerGender("test");
        p1.setType(Pronunciation.type.SAMPLE);

        pronunciationRepository.save(p1);

        // get word by id from repository
        // by JPA repository by id returning optional container Word/Null
        Optional<Word> optionalWord  =  wordRepository.findById("8f7d1b9e3a2c5f6e");
        System.out.println(optionalWord);

        if  (optionalWord.isPresent()) {
            // get word from optional container
            p1.setWord(optionalWord.get());
            pronunciationRepository.save(p1);
        }



        // pronunciationRepository.save(p1);
        // w1.getPronunciations().add(p1);
        // wordRepository.save(w1);

    }
}
