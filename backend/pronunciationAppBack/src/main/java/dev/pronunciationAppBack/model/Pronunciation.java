package dev.pronunciationAppBack.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pronunciation {
    @Id
    private String id;
    private String audioDescription;
    private long audioDuration;
    private long audioSize;
    private String audioUrl;
    private String definition;
    private String phoneticSpelling;
    private String speakerGender;
    public enum type {
        RECORDED, SAMPLE
    }
    private type type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "WORD_ID_FK")
    private Word word;



}
