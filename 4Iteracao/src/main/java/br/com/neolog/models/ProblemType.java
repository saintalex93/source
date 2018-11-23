package br.com.neolog.models;

public enum ProblemType
{

    VOLUME
    {
        @Override
        public long getValue(
            final Product product )
        {
            return product.getVolume();
        }
    },
    VALUE
    {
        @Override
        public long getValue(
            final Product product )
        {
            return product.getPrice();
        }
    },
    WEIGHT
    {
        @Override
        public long getValue(
            final Product product )
        {
            return product.getWeight();
        }
    };

    public abstract long getValue(
        Product product );
}
