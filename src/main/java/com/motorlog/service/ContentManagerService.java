package com.motorlog.service;


import com.motorlog.entity.ContentManager;
import com.motorlog.repository.ContentManagerRepository;
import com.motorlog.security.Authority;
import com.motorlog.security.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ContentManagerService {

    //Managed repository ---------------------------------

    @Autowired
    private ContentManagerRepository contentManagerRepository;

    //Supporting services --------------------------------

    @Autowired
    private ActorService		actorService;

    //Simple CRUD Methods --------------------------------

    public ContentManager create() {
        final Authority a = new Authority();
        a.setAuthority(Authority.CONTENTMANAGER);
        final UserAccount account = new UserAccount();
        account.setAuthorities(Arrays.asList(a));

        final ContentManager contentManager = new ContentManager();

        contentManager.setUserAccount(account);
        contentManager.getUserAccount().setBanned(false);

        return contentManager;
    }

    public ContentManager findOne(final int id) {
        ContentManager contentManager = null;
        Optional<ContentManager> optionalContentManager = this.contentManagerRepository.findById(id);
        if(optionalContentManager.isPresent())
            contentManager = optionalContentManager.get();

        return contentManager;
    }

    public Collection<ContentManager> findAll() {
        return this.contentManagerRepository.findAll();
    }

    public ContentManager save(ContentManager contentManager){
        Assert.notNull(contentManager, "ContentManager is null");
        ContentManager saved2;

        if(contentManager.getId() != 0){
            Assert.isTrue(this.actorService.findByPrincipal().getId() == contentManager.getId(), "Different actor");
            saved2 = this.contentManagerRepository.save(contentManager);
        }else{
            ContentManager saved = this.contentManagerRepository.save(contentManager);
            saved2 = this.contentManagerRepository.save(saved);
        }

        return saved2;
    }


    public void delete(ContentManager contentManager){
        Assert.notNull(contentManager, "ContentManager is null");
        Assert.isTrue(this.actorService.findByPrincipal().getId() == contentManager.getId(), "Different actor");

        this.contentManagerRepository.delete(contentManager);
    }

    //Other methods

    /*//Top-5 garages in terms of revisions and repairs
    public Collection<Garage> top5GaragesInTermsOfRevisions(){
        return this.garageRepository.top5GaragesInTermsOfRevisions();
    }*/
}

