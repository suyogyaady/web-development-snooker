package com.system.snooker.Service;

import com.system.snooker.Pojo.SnookerPojo;
import com.system.snooker.entity.Snooker;

import java.io.IOException;
import java.util.List;

public interface SnookerService {
    SnookerPojo savesnooker(SnookerPojo snookerPojo) throws IOException;

    Snooker fetchById(Integer id);

    List<Snooker> fetchAll();

    void deleteById(Integer id);
}
