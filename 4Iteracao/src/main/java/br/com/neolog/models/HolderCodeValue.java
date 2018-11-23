package br.com.neolog.models;

public class HolderCodeValue
{
    private String code;
    private long value;

    public HolderCodeValue()
    {
    }

    public static HolderCodeValue create(
        final String code,
        final long price )
    {
        return new HolderCodeValue( code, price );
    }

    private HolderCodeValue(
        final String code,
        final long price )
    {
        super();
        this.code = code;
        this.value = price;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(
        final String code )
    {
        this.code = code;
    }

    public long getValue()
    {
        return value;
    }

    public void setValue(
        final long price )
    {
        this.value = price;
    }

}
