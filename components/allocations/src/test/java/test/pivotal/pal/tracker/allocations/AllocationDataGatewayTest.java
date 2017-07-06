package test.pivotal.pal.tracker.allocations;

import io.pivotal.pal.tracker.allocations.data.AllocationDataGateway;
import io.pivotal.pal.tracker.allocations.data.AllocationFields;
import io.pivotal.pal.tracker.allocations.data.AllocationRecord;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static io.pivotal.pal.tracker.allocations.data.AllocationFields.allocationFieldsBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class AllocationDataGatewayTest {

    private AllocationDataGateway gateway = new AllocationDataGateway();

    @Test
    public void testCreate() {
        AllocationFields fields = allocationFieldsBuilder()
            .projectId(22L)
            .userId(12L)
            .firstDay(LocalDate.parse("2016-01-13"))
            .lastDay(LocalDate.parse("2016-09-17"))
            .build();

        AllocationRecord created = gateway.create(fields);

        assertThat(created.id).isNotNull();
        assertThat(created.projectId).isEqualTo(22L);
        assertThat(created.userId).isEqualTo(12L);
        assertThat(created.firstDay).isEqualTo(LocalDate.parse("2016-01-13"));
        assertThat(created.lastDay).isEqualTo(LocalDate.parse("2016-09-17"));


        AllocationRecord found = gateway.find(created.id);

        assertThat(found.projectId).isEqualTo(22L);
        assertThat(found.userId).isEqualTo(12L);
        assertThat(found.firstDay).isEqualTo(LocalDate.parse("2016-01-13"));
        assertThat(found.lastDay).isEqualTo(LocalDate.parse("2016-09-17"));
    }

    @Test
    public void testFindAllByProjectId() {
        AllocationRecord myAllocation = gateway.create(allocationFieldsBuilder()
            .projectId(22L)
            .userId(12L)
            .firstDay(LocalDate.parse("2016-01-13"))
            .lastDay(LocalDate.parse("2016-09-17"))
            .build());

        gateway.create(allocationFieldsBuilder()
            .projectId(999L)
            .userId(12L)
            .firstDay(LocalDate.parse("2016-01-13"))
            .lastDay(LocalDate.parse("2016-09-17"))
            .build());

        List<AllocationRecord> result = gateway.findAllByProjectId(22L);

        assertThat(result).containsExactly(myAllocation);
    }
}
