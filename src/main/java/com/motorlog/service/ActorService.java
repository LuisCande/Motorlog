package com.motorlog.service;

import com.motorlog.entity.Actor;
import com.motorlog.repository.ActorRepository;
import com.motorlog.security.UserAccount;
import com.motorlog.security.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ActorService {

    // Managed repository

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public Collection<Actor> findAll() {
        return this.actorRepository.findAll();
    }

    public Optional<Actor> findOne(final int id) {
        Assert.notNull(id, "The id is null.");

        return this.actorRepository.findById(id);
    }

    public Actor save(final Actor actor) {
        Assert.notNull(actor, "The actor is null.");

        final Actor saved = this.actorRepository.save(actor);

        return saved;
    }

    public void delete(final Actor actor) {
        Assert.notNull(actor, "The actor is null.");

        Assert.isTrue(this.getPrincipal().getId() == actor.getId(), "The actor id isn't equal to the principal id.");

        this.actorRepository.delete(actor);
    }

    public void hashPassword(final Actor a) {
        final String oldPs = a.getUserAccount().getPassword();
        final PasswordEncoder encoder = new BCryptPasswordEncoder(11);
        final String hash = encoder.encode(oldPs);
        final UserAccount old = a.getUserAccount();
        old.setPassword(hash);
        final UserAccount newOne = this.userAccountRepository.save(old);
        a.setUserAccount(newOne);
    }

    public static UserAccount getPrincipal() {
        UserAccount result;
        SecurityContext context;
        Authentication authentication;
        Object principal;

        // If the asserts in this method fail, then you're
        // likely to have your Tomcat's working directory
        // corrupt. Please, clear your browser's cache, stop
        // Tomcat, update your Maven's project configuration,
        // clean your project, clean Tomcat's working directory,
        // republish your project, and start it over.

        context = SecurityContextHolder.getContext();
        Assert.notNull(context, "The context is null.");
        authentication = context.getAuthentication();
        Assert.notNull(authentication, "The authentication is null.");
        principal = authentication.getPrincipal();
        Assert.isTrue(principal instanceof UserAccount, "The principal isn't an instance of user account.");
        result = (UserAccount) principal;
        Assert.notNull(result, "The principal is null.");
        Assert.isTrue(result.getId() != 0, "The principal id equals zero.");

        return result;
    }

    public Actor findByPrincipal() {
        final UserAccount userAccount = this.getPrincipal();
        Assert.notNull(userAccount,"The principal is null");
        final Actor a = this.actorRepository.findByUserAccountId(userAccount.getId());
        return a;
    }
}
