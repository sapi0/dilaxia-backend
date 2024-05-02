package com.sapi0.dilaxiabackend.data.dto;

import java.sql.Timestamp;
import java.util.List;

public class SubscriptionsDTO {

    public EventLightDTO event;
    public List<EventUserDTO> queued;
    public List<EventUserDTO> subscribed;
    public Timestamp timestamp;

}
