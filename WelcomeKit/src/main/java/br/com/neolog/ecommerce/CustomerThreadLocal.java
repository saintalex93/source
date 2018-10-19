package br.com.neolog.ecommerce;

import br.com.neolog.ecommerce.customer.Customer;

public class CustomerThreadLocal
{

    private static final ThreadLocal<Customer> userThreadLocal = new ThreadLocal<Customer>();

    public static void set(
        final Customer customer )
    {
        userThreadLocal.set( customer );
    }

    public static Customer get()
    {
        return userThreadLocal.get();
    }

    public static void destroy()
    {
        userThreadLocal.remove();
    }

}
