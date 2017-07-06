package io.pivotal.pal.tracker.backlog.data;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StoryDataGateway {

    private final Map<Long, StoryRecord> db = new HashMap<>();
    private long currentId = 0;

    public StoryRecord create(StoryFields fields) {
        long id = ++currentId;

        StoryRecord record = StoryRecord.storyRecordBuilder().id(id).projectId(fields.projectId).name(fields.name).build();

        db.put(id, record);

        return record;
    }

    public List<StoryRecord> findAllByProjectId(Long projectId) {
        return db.values().stream().filter(st -> st.projectId == projectId).collect(Collectors.toList());
    }

    public StoryRecord find(long id) {
        return db.get(id);
    }

}
