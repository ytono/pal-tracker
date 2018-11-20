package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private long keyId = 1L;

    @Override
    public TimeEntry create(TimeEntry createEntry) {
        Long id = keyId++;
        TimeEntry newEntry = new TimeEntry(id, createEntry.getProjectId(), createEntry.getUserId(),
                createEntry.getDate(), createEntry.getHours());
        timeEntryMap.put(id, newEntry);
        return find(id);
    }

    @Override
    public TimeEntry find(Long id) {
        TimeEntry res = timeEntryMap.get(id);
        if (res != null) {
            return res;
        }
        return null;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry updateEntry) {
        TimeEntry entry = find(id);
        if (entry != null) {
            entry.setProjectId(updateEntry.getProjectId());
            entry.setUserId(updateEntry.getUserId());
            entry.setDate(updateEntry.getDate());
            entry.setHours(updateEntry.getHours());
            timeEntryMap.replace(id, entry);
        }
        return entry;
    }

    @Override
    public void delete(Long id) {
        if (find(id) != null) {
            timeEntryMap.remove(id);
        }
    }
}
