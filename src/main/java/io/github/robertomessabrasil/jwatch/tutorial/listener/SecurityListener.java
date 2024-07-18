package io.github.robertomessabrasil.jwatch.tutorial.listener;

import io.github.robertomessabrasil.jwatch.observer.listener.Event;
import io.github.robertomessabrasil.jwatch.observer.listener.EventListener;
import io.github.robertomessabrasil.jwatch.tutorial.security.event.InvalidRoleEvent;

public class SecurityListener extends EventListener {
    @Override
    public boolean handleEvent(Event event) {
        if (event instanceof InvalidRoleEvent) {
            return true;
        }
        return false;
    }
}
