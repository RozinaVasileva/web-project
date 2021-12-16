package bg.softuni.yacht.service;

import bg.softuni.yacht.model.service.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(String action, Long tourId);

    List<LogServiceModel> findAllLogs();
}
