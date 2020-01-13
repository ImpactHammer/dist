package com.example.servingwebcontent.utils;

import com.example.servingwebcontent.entity.Device;
import com.example.servingwebcontent.entity.DeviceInstance;
import com.example.servingwebcontent.repository.DeviceInstanceRepository;
import com.example.servingwebcontent.repository.DeviceRepository;
import com.example.servingwebcontent.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    DeviceInstanceRepository deviceInstanceRepository;
    MasterRepository masterRepository;
    DeviceRepository deviceRepository;

    @Autowired
    public DataLoader(DeviceInstanceRepository deviceInstanceRepository, MasterRepository masterRepository,
                      DeviceRepository deviceRepository) {
        this.deviceInstanceRepository = deviceInstanceRepository;
        this.masterRepository = masterRepository;
        this.deviceRepository = deviceRepository;
    }

    public void run(ApplicationArguments args) {
        Device deviceMonitor = new Device("Monitor");
        Device devicePrinter = new Device("Printer");
        Device deviceScanner = new Device("Scanner");

        deviceRepository.save(deviceMonitor);
        deviceRepository.save(devicePrinter);
        deviceRepository.save(deviceScanner);

//        DeviceInstance deviceInstanceMonitor = new DeviceInstance(null, null, "free");
//        DeviceInstance deviceInstancePrinter = new DeviceInstance(null, null, "free");
//        DeviceInstance deviceInstanceScanner = new DeviceInstance(null, null, "free");

        deviceInstanceRepository.save(new DeviceInstance(deviceMonitor, null, "free"));
        deviceInstanceRepository.save(new DeviceInstance(devicePrinter, null, "free"));
        deviceInstanceRepository.save(new DeviceInstance(deviceScanner, null, "free"));
    }
}