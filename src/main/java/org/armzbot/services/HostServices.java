package org.armzbot.services;

import org.armzbot.models.Host;
import org.armzbot.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HostServices {

    private final HostRepository hostRepository;

    @Autowired
    public HostServices(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> getAllHost() {
        return hostRepository.findAll();
    }

    public Host getHostById(String id) throws IOException {
        if (id == null) {
            throw new IOException();
        }
        Optional<Host> repository = hostRepository.findById(id);
        if (!repository.isPresent()) {
            throw new IOException();
        }
        return repository.get();

    }
}
