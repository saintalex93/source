package br.com.neolog.models;

import com.google.common.base.MoreObjects;

public class HolderCodeValue
{
    private String code;
    private long value;

    public HolderCodeValue()
    {
    }

    public static HolderCodeValue create(
        final String code,
        final long value )
    {
        return new HolderCodeValue( code, value );
    }

    private HolderCodeValue(
        final String code,
        final long value )
    {
        super();
        this.code = code;
        this.value = value;
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
        final long value )
    {
        this.value = value;
    }

    @Override
    public String toString()
    {

        return MoreObjects.toStringHelper( this.getClass() ).add( "CODE", code ).add( "VALUE", value ).toString();

    }

}
