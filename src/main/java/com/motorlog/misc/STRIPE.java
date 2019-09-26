package com.motorlog.misc;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Plan;
import com.stripe.model.Product;
import com.stripe.model.Subscription;

import java.util.HashMap;
import java.util.Map;

/***************************************************************************************************/
/* The process for using the Stripe API is as follows:                                             */
/* - Create a product using the createProduct method. This only needs to be done once, as we are   */
/*   only selling a single thing, the capability of making use of our application.                 */
/* - Create a plan that defines the details of the subscription to the product. This only needs    */
/*   to be done once and, according to our business proposal, we will need a monthly and a yearly. */
/* - Follow the steps in https://stripe.com/docs/recipes/subscription-signup and make use of the   */
/*   createCustomer and subscribe methods. We should store the customer ID for the user.           */
/* - While testing, real credit cards can't be used. https://stripe.com/docs/testing#cards         */
/* - You can delete all test data. https://dashboard.stripe.com/account/data                       */
/***************************************************************************************************/

// https://dashboard.stripe.com
// email: motorlogapplication@gmail.com
// password: ISPP_SOBRESALIENTE

public class STRIPE {

    // https://dashboard.stripe.com/account/apikeys
    private static String test_key = "sk_test_FCfErmvG8COUqFM4js65l8JE000XtIkhLW";

    // https://stripe.com/docs/recipes/subscription-signup
    public static Subscription subscribe(String customerId, String planId) throws StripeException {
        Stripe.apiKey = test_key;
        Map<String, Object> item = new HashMap<>();
        item.put("plan", planId);
        Map<String, Object> items = new HashMap<>();
        items.put("0", item);
        Map<String, Object> params = new HashMap<>();
        params.put("customer", customerId);
        params.put("items", items);
        return Subscription.create(params);
    }

    // https://stripe.com/docs/billing/subscriptions/canceling-pausing
    public static Subscription unsubscribe(String subscriptionId) throws StripeException {
        Stripe.apiKey = test_key;
        Subscription subscription = Subscription.retrieve(subscriptionId);
        return subscription.cancel();
    }

    // https://stripe.com/docs/api/customers
    public static Customer createCustomer(String email, String source) throws StripeException {
        Stripe.apiKey = test_key;
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("source", source);
        return Customer.create(params);
    }

    // https://stripe.com/docs/api/plans
    public static Plan createPlan(
            String productId, String nickname, String interval,
            String currency, double amount) throws StripeException {
        Stripe.apiKey = test_key;
        Map<String, Object> params = new HashMap<>();
        params.put("product", productId);
        params.put("nickname", nickname);
        params.put("interval", interval);
        params.put("currency", currency);
        params.put("amount", amount);
        return Plan.create(params);
    }

    // https://stripe.com/docs/api/service_products/create
    public static Product createProduct(String name) throws StripeException {
        Stripe.apiKey = test_key;
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("type", "service");
        return Product.create(params);
    }

    public static Subscription getSubscription(String id) throws StripeException {
        return Subscription.retrieve(id);
    }

    public static Customer getCustomer(String id) throws StripeException {
        return Customer.retrieve(id);
    }

    public static Plan getPlan(String id) throws StripeException {
        return Plan.retrieve(id);
    }

    public static Product getProduct(String id) throws StripeException {
        return Product.retrieve(id);
    }
}
