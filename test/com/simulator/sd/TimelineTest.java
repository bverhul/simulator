package com.simulator.sd;

import com.simulator.events.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TimelineTest {
    /***
     * On veut vérifier que les évènements sont triés selon t
     */
    @org.junit.jupiter.api.Test
    void sortedEvent(){
        Event e1 = new Event(10);
        Event e2 = new Event(5);
        Event e3 = new Event(20);
        Event e4 = new Event(3);
        Timeline timeline = new Timeline();
        timeline.addEvent(e1);timeline.addEvent(e2);
        timeline.addEvent(e3);timeline.addEvent(e4);
        System.out.println(timeline.toString());

        assertEquals(e4,timeline.getNextEvent());
        assertEquals(e2,timeline.getNextEvent());
        assertEquals(e1,timeline.getNextEvent());
        assertEquals(e3,timeline.getNextEvent());
        assertNull(timeline.getNextEvent());
    }

}