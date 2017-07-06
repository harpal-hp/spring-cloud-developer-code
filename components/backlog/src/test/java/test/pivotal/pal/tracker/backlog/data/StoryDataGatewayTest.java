package test.pivotal.pal.tracker.backlog.data;

import io.pivotal.pal.tracker.backlog.data.StoryDataGateway;
import io.pivotal.pal.tracker.backlog.data.StoryFields;
import io.pivotal.pal.tracker.backlog.data.StoryRecord;
import org.junit.Test;

import java.util.List;

import static io.pivotal.pal.tracker.backlog.data.StoryFields.storyFieldsBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class StoryDataGatewayTest {

    private StoryDataGateway gateway = new StoryDataGateway();

    @Test
    public void testCreate() {
        StoryFields fields = storyFieldsBuilder()
            .projectId(22L)
            .name("aStory")
            .build();

        StoryRecord created = gateway.create(fields);

        assertThat(created.id).isNotNull();
        assertThat(created.name).isEqualTo("aStory");
        assertThat(created.projectId).isEqualTo(22L);

        StoryRecord persisted = gateway.find(created.id);

        assertThat(persisted.projectId).isEqualTo(22L);
        assertThat(persisted.name).isEqualTo("aStory");
    }

    @Test
    public void testFindBy() {

        StoryRecord story = gateway.create(storyFieldsBuilder()
            .projectId(22L)
            .name("aStory")
            .build());

        gateway.create(storyFieldsBuilder()
            .projectId(999L)
            .name("bStory")
            .build());

        List<StoryRecord> result = gateway.findAllByProjectId(22L);

        assertThat(result).containsExactly(story);
    }
}
