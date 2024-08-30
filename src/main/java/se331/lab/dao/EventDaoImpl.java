package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();

        eventList.add(Event.builder()
                .id(1L)
                .category("Concert")
                .title("Rock on the Hill")
                .description("Rock concert")
                .location("Moon Hill")
                .date("2021-08-30")
                .time("15:00")
                .organizer("The Rocker")
                .petAllowed(false)
                .build());

        eventList.add(Event.builder()
                .id(2L)
                .category("Sport")
                .title("Swim with the Shark")
                .description("Swimming with the shark in the ocean")
                .location("Ocean")
                .date("2021-08-31")
                .time("09:00")
                .organizer("The Shark")
                .petAllowed(false)
                .build());

        eventList.add(Event.builder()
                .id(3L)
                .category("Sport")
                .title("Run in the Jungle")
                .description("Running in the jungle")
                .location("Jungle")
                .date("2021-09-01")
                .time("06:00")
                .organizer("The Runner")
                .petAllowed(false)
                .build());

        eventList.add(Event.builder()
                .id(4L)
                .category("Sport")
                .title("Bike in the Mountain")
                .description("Biking in the mountain")
                .location("Mountain")
                .date("2021-09-02")
                .time("07:00")
                .organizer("The Biker")
                .petAllowed(false)
                .build());

        eventList.add(Event.builder()
                .id(5L)
                .category("Concert")
                .title("Pop on the Beach")
                .description("Pop concert")
                .location("Sun Beach")
                .date("2021-09-03")
                .time("16:00")
                .organizer("The Popper")
                .petAllowed(false)
                .build());

        eventList.add(Event.builder()
                .id(6L)
                .category("Sport")
                .title("Soccer in the Field")
                .description("Soccer match")
                .location("Field")
                .date("2021-09-04")
                .time("17:00")
                .organizer("The Soccer")
                .petAllowed(false)
                .build());
    }

    @Override
    public Integer getEventSize() {
        return eventList.size();
    }

    @Override
    public List<Event> getEvents(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? eventList.size() : pageSize;
        page = page == null ? 1 : page;

        int firstIndex = (page - 1) * pageSize;

        return eventList.subList(firstIndex, firstIndex + pageSize);
    }

    @Override
    public Event getEvent(Long id) {
        return eventList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }
}
