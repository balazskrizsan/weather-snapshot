package com.kbalazsworks.weathersnapshot.service;

import com.kbalazsworks.weathersnapshot.repository.ParsedTemperatureSnapshotsRepository;
import com.kbalazsworks.weathersnapshot.valueobject.TemperatureSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsedTemperatureSnapshotsService
{
    private ParsedTemperatureSnapshotsRepository parsedTemperatureSnapshotsRepository;

    @Autowired
    public void setParsedTemperatureSnapshotsRepository(
        ParsedTemperatureSnapshotsRepository parsedTemperatureSnapshotsRepository
    )
    {
        this.parsedTemperatureSnapshotsRepository = parsedTemperatureSnapshotsRepository;
    }

    public void bulkInsert(List<TemperatureSnapshot> temperatureSnapshots)
    {
        parsedTemperatureSnapshotsRepository.bulkInsert(temperatureSnapshots);
    }
}
