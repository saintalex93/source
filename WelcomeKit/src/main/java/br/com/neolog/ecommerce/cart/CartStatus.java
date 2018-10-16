package br.com.neolog.ecommerce.cart;

public enum CartStatus
{
    ACTIVE( "Active" ),
    CANCELLED( "Cancelled" ),
    FINISHED( "Finished" );

    private final String status;

    CartStatus(
        final String cartStatus )
    {
        this.status = cartStatus;
    }

    public String getStatus()
    {
        return this.status;
    }

}
