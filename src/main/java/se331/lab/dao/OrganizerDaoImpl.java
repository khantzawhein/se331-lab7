package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Organizer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizers;

    @PostConstruct
    public void init() {
        organizers = new ArrayList<>();

        organizers.add(Organizer.builder()
                .id(1L)
                .name("The Rocker")
                .organization("Rock")
                .address("Moon Hill")
                .build());

        organizers.add(Organizer.builder()
                .id(2L)
                .name("The Shark")
                .organization("Shark")
                .address("Ocean")
                .build());

        organizers.add(Organizer.builder()
                .id(3L)
                .name("The Runner")
                .organization("Runner")
                .address("Jungle")
                .build());

        organizers.add(Organizer.builder()
                .id(4L)
                .name("The Biker")
                .organization("Biker")
                .address("Mountain")
                .build());

        organizers.add(Organizer.builder()
                .id(5L)
                .name("The Climber")
                .organization("Climber")
                .address("Mountain")
                .build());

        organizers.add(Organizer.builder()
                .id(6L)
                .name("The Diver")
                .organization("Diver")
                .address("Ocean")
                .build());
    }

    @Override
    public Integer getOrganizerSize() {
        return organizers.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizers.size() : pageSize;
        page = page == null ? 1 : page;

        int firstIndex = (page - 1) * pageSize;

        return organizers.subList(firstIndex, firstIndex + pageSize);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizers.stream().filter(organizer -> organizer.getId().equals(id)).findFirst().orElse(null);
    }
}
