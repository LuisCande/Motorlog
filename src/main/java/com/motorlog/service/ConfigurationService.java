package com.motorlog.service;


import com.motorlog.entity.Configuration;
import com.motorlog.repository.ConfigurationRepository;
import com.motorlog.security.Authority;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ConfigurationService {

    //Managed repository

    private ConfigurationRepository configurationRepository;

    //Supported services
    private ActorService actorService;

    public ConfigurationService(ConfigurationRepository configurationRepository, ActorService actorService) {
        this.configurationRepository = configurationRepository;
        this.actorService = actorService;
    }

    public Configuration findOne(final int id) {
        Configuration configuration= null;
        Optional<Configuration> optionalConfiguration = this.configurationRepository.findById(id);
        if(optionalConfiguration.isPresent()){
            configuration = optionalConfiguration.get();
        }
        return configuration;
    }

    public Collection<Configuration> findAll() {
        return configurationRepository.findAll();
    }

    public Configuration save(final Configuration c) {
        Assert.notNull(c, "Configuration is null.");
        Authority a = new Authority();
        a.setAuthority(Authority.ADMIN);
        Assert.isTrue(this.actorService.findByPrincipal().getUserAccount().getAuthorities().contains(a), "The principal user isn't a content manager.");
        return configurationRepository.save(c);
    }

    public void delete(final Configuration c) {
        Assert.notNull(c, "Item is null.");
        configurationRepository.delete(c);

    }
}
