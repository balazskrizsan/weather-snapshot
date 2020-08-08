package com.kbalazsworks.weathersnapshot.repository;

import com.kbalazsworks.weathersnapshot.db.tables.ParsedTemperatureSnapshots;
import com.kbalazsworks.weathersnapshot.valueobject.TemperatureSnapshot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParsedTemperatureSnapshotsRepository extends AbstractRepository
{
    private final ParsedTemperatureSnapshots parsedTemperatureSnapshotsTable
        = ParsedTemperatureSnapshots.PARSED_TEMPERATURE_SNAPSHOTS;

    public void bulkInsert(List<TemperatureSnapshot> temperatureSnapshots)
    {
        temperatureSnapshots.forEach(
            temperatureSnapshot ->
            {
                getQueryBuilder()
                    .insertInto(
                        parsedTemperatureSnapshotsTable,
                        parsedTemperatureSnapshotsTable.HTML_LOGS_ID,
                        parsedTemperatureSnapshotsTable.TEMPERATURE_TIME,
                        parsedTemperatureSnapshotsTable.TEMPERATURE_MIN,
                        parsedTemperatureSnapshotsTable.TEMPERATURE_MAX
                    )
                    .values(
                        temperatureSnapshot.getHtmlLogId(),
                        temperatureSnapshot.getTemperatureTime(),
                        temperatureSnapshot.getMinTemperature(),
                        temperatureSnapshot.getMaxTemperature()
                    )
                    .execute();
            }
        );
    }
}
