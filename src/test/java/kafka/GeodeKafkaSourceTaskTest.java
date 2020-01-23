package kafka;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kafka.GeodeConnectorConfig.REGION_NAME;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GeodeKafkaSourceTaskTest {

    @Test
    public void cqListenerOnEventPopulatesEventsBuffer() {

    }

    @Test
    public void pollReturnsEventsWhenEventBufferHasValues() {

    }

    @Test
    public void regionsArePassedCorrectlyToTask() {

    }

    @Test
    public void installOnGeodeShouldCallCq() {
        GeodeKafkaSourceTask task = new GeodeKafkaSourceTask();
    }



    @Test
    public void parseRegionNamesShouldSplitOnComma() {
        GeodeKafkaSourceTask task = new GeodeKafkaSourceTask();
        List<String> regionNames = task.parseNames("region1,region2,region3,region4");
        assertEquals(4, regionNames.size());
        assertThat(true, allOf(is(regionNames.contains("region1"))
                , is(regionNames.contains("region2"))
                , is(regionNames.contains("region3"))
                , is(regionNames.contains("region4"))));
    }

    @Test
    public void parseRegionNamesShouldChomp() {
        GeodeKafkaSourceTask task = new GeodeKafkaSourceTask();
        List<String> regionNames = task.parseNames("region1, region2, region3,region4");
        assertEquals(4, regionNames.size());
        assertThat(true, allOf(is(regionNames instanceof List)
                , is(regionNames.contains("region1"))
                , is(regionNames.contains("region2"))
                , is(regionNames.contains("region3"))
                , is(regionNames.contains("region4"))));
    }

    @Test
    public void createSourcePartitionsShouldReturnAMapOfSourcePartitions() {
        GeodeKafkaSourceTask task = new GeodeKafkaSourceTask();
        List<String> regionNames = Arrays.asList(new String[]{"region1", "region2", "region3"});
        Map<String, Map<String,String>> sourcePartitions = task.createSourcePartitionsMap(regionNames);
        assertThat(3, is(sourcePartitions.size()));
        assertThat(true, is(sourcePartitions.get("region1").get(REGION_NAME).equals("region1")));
        assertThat(true, is(sourcePartitions.get("region2").get(REGION_NAME).equals("region2")));
        assertThat(true, is(sourcePartitions.get("region3").get(REGION_NAME).equals("region3")));
    }

    @Test
    public void shouldBeAbleToParseGeodeLocatorStrings() {
        GeodeKafkaSourceTask task = new GeodeKafkaSourceTask();
        String locatorString="localhost[8888], localhost[8881]";
        List<LocatorHostPort> locators = task.parseLocators(locatorString);
        assertThat(2, is(locators.size()));
    }

    @Test
    public void listOfLocatorsShouldBeConfiguredIntoClientCache() {

    }

    @Test
    public void shouldNotBeDurableIfDurableClientIdIsNull() {

    }

    @Test
    public void shouldNotCallReadyForEventsIfDurableClientPrefixIsEmpty() {

    }

    //Source properties tests
    @Test
    public void propertiesShouldBeCorrectlyTranslatedToConfiguration() {
        Map<String, String> props = new HashMap<>();
        props.put(GeodeConnectorConfig.QUEUE_SIZE, GeodeConnectorConfig.DEFAULT_QUEUE_SIZE);
        props.put(GeodeConnectorConfig.BATCH_SIZE, GeodeConnectorConfig.DEFAULT_BATCH_SIZE);


        GeodeKafkaSourceTask task = new GeodeKafkaSourceTask();
        task.start(props);

//        assertThat(task.getQueueSize(GeodeConnectorConfig.QUEUE_SIZE));


    }


}
