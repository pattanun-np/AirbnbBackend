package org.armzbot.services;

import org.armzbot.models.Host;
import org.armzbot.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
            throw new IOException("input is null");
        }
        Optional<Host> repository = hostRepository.findById(id);
        if (!repository.isPresent()) {
            throw new IOException("not found");
        }
        Host host = repository.get();
        if (!host.isActive()) {
            throw new IOException("not found");
        }
        return host;
    }

    public void addHost(@RequestBody Host host) throws IOException {
        if (host.hasNull()) {
            throw new IOException("input is null");
        }
        if (hostRepository.findById(host.getHost_id()).isPresent()
                && hostRepository.findById(host.getHost_id()).get().isActive()) {
            throw new IOException("id has already been");
        }
        hostRepository.save(host);
    }
    public void updateHostById(String id, String username,
                               String firstname, String lastname,
                               String email, String phone) throws IOException {

        if (id == null || (username == null && firstname == null
                && lastname == null && email == null && phone == null)) {
            throw new IOException("input is null");
        }

        Optional<Host> repository = hostRepository.findById(id);

        if (!repository.isPresent()) {
            throw new IOException("not found");
        }

        Host host = repository.get();

        if (!host.isActive()) {
            throw new IOException("not found");
        }
        if (username != null) {
            host.setUsername(username);
        }
        if (firstname != null) {
            host.setFirstname(firstname);
        }
        if (lastname != null) {
            host.setLastname(lastname);
        }
        if (email != null) {
            host.setEmail(email);
        }
        if (phone != null) {
            host.setPhone(phone);
        }

        hostRepository.save(host);
    }
    public void deleteHostById(String id) throws IOException {
        if (id == null) {
            throw new IOException();
        }
        Optional<Host> repository = hostRepository.findById(id);
        if (!repository.isPresent()) {
            throw new IOException("not found");
        }
        Host host = repository.get();
        if (!host.isActive()) {
            throw new IOException("not found");
        }
        host.setActive(false);
        hostRepository.save(host);
    }
}
