package io.pivotal.pal.tracker.allocations.data;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AllocationDataGateway {

    private final Map<Long, AllocationRecord> db = new HashMap<>();
    private long currentId = 0;


    public AllocationRecord create(AllocationFields fields) {
        long id = ++currentId;

        AllocationRecord record = AllocationRecord.allocationRecordBuilder()
            .firstDay(fields.firstDay)
            .id(id)
            .lastDay(fields.lastDay)
            .projectId(fields.projectId)
            .userId(fields.userId)
            .build();

        db.put(id, record);

        return record;
    }

    public List<AllocationRecord> findAllByProjectId(Long projectId) {
        return db.values().stream().filter( r -> r.projectId == projectId).collect(Collectors.toList());
    }


    public AllocationRecord find(long id) {
        return db.get(id);
    }
}
