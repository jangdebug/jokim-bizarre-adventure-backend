package com.jokim.sivillage.api.event.domain;

import com.jokim.sivillage.api.image.Image;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class EventImageList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private Boolean isThumbnail;


}
