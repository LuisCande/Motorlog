package com.motorlog.service;

import com.motorlog.entity.Garage;
import com.motorlog.misc.STRIPE;
import com.motorlog.repository.GarageRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubscriptionService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private ActorService actorService;

    public boolean isSubscribed() {
        int id = actorService.findByPrincipal().getId();
        Optional<Garage> optional = garageRepository.findById(id);
        if (!optional.isPresent()) return true; // Any other roles need not be limited by this.
        else {
            Garage garage = optional.get();
            if (garage.getCustomer() == null || garage.getCustomer().isEmpty()) return false;
            try {
                Customer customer = STRIPE.getCustomer(garage.getCustomer());
                List<Subscription> subscriptions = customer.getSubscriptions().getData();
                for (Subscription subscription : subscriptions) {
                    String status = subscription.getStatus();
                    if (status.equals("active")) return true;
                } return false;
            } catch (StripeException e) {
                System.out.println(e.getStripeError().getMessage());
                return false;
            }
        }
    }

    public boolean subscribe(String token, String planId) {
        int id = actorService.findByPrincipal().getId();
        Optional<Garage> optional = garageRepository.findById(id);
        if (!optional.isPresent()) return false;
        else {
            Garage garage = optional.get();
            try {
                Customer customer = STRIPE.createCustomer(garage.getEmail(), token);
                STRIPE.subscribe(customer.getId(), planId);
                garage.setCustomer(customer.getId());
                garageRepository.save(garage);
                return true;
            } catch (StripeException e) {
                System.out.println(e.getStripeError().getMessage());
                return false;
            }
        }
    }

    public boolean unsubscribe() {
        int id = actorService.findByPrincipal().getId();
        Optional<Garage> optional = garageRepository.findById(id);
        if (!optional.isPresent()) return false;
        else {
            Garage garage = optional.get();
            try {
                boolean result = false;
                Customer customer = STRIPE.getCustomer(garage.getCustomer());
                List<Subscription> subscriptions = customer.getSubscriptions().getData();
                for (Subscription subscription : subscriptions) {
                    String status = subscription.getStatus();
                    if (status.equals("active")) {
                        STRIPE.unsubscribe(subscription.getId());
                        result = true;
                    }
                } return result;
            } catch (StripeException e) {
                System.out.println(e.getStripeError().getMessage());
                return false;
            }
        }
    }
}
