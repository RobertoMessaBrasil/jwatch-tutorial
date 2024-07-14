package com.jwatch.tutorial.listener;

import com.jwatch.observer.listener.Event;
import com.jwatch.observer.listener.EventListener;
import com.jwatch.tutorial.security.event.InvalidRoleEvent;

public class SecurityListener extends EventListener {
    @Override
    public boolean handleEvent(Event event) {
        if (event instanceof InvalidRoleEvent) {
            return true;
        }
        return false;
    }
}
