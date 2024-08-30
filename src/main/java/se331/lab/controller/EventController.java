package se331.lab.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;
import se331.lab.service.EventService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {
    final EventService eventService;
    HttpHeaders responseHeader = new HttpHeaders();

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        List<Event> output = null;
        responseHeader.set("x-total-count", String.valueOf(eventService.getEventSize()));
        try {
            output = eventService.getEvents(perPage, page);
            return ResponseEntity.ok().headers(responseHeader).body(output);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(output, responseHeader, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = eventService.getEvent(id);

        if (output == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given ID is not found");
        }
        return ResponseEntity.ok(output);
    }
}
