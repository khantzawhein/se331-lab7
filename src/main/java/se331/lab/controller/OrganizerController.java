package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se331.lab.service.OrganizerService;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;
    HttpHeaders httpHeaders = new HttpHeaders();

    @GetMapping("organizers")
    public ResponseEntity<?> getOrganizers(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page
    ) {
        httpHeaders.set("x-total-count", String.valueOf(organizerService.getOrganizerSize()));
        try {
            return ResponseEntity.ok().headers(httpHeaders).body(organizerService.getOrganizers(perPage, page));
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(404).body(new ArrayList<>());
        }
    }

    @GetMapping("organizers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(organizerService.getOrganizer(id));
    }
}
