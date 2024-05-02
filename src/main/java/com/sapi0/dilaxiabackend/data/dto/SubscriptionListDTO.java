package com.sapi0.dilaxiabackend.data.dto;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionListDTO {

    public EventLightDTO event;
    public List<EventUserDTO> queued = new ArrayList<>();
    public List<EventUserDTO> subscribed = new ArrayList<>();

    public SubscriptionListDTO(EventLightDTO event, List<EventUserDTO> queued, List<EventUserDTO> subscribed) {
        this.event = event;
        this.queued = queued;
        this.subscribed = subscribed;
    }

    public SubscriptionListDTO() {

    }

}
