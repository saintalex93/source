package br.com.neolog.ecommerce.optimization.problem;

public class PreProblem
{

    public PreProblem(
        final int code,
        final long value,
        final int present )
    {
        this.code = code;
        this.value = value;
        this.present = present;
    }

    private int code;
    private long value;
    private int present;

    public int getCode()
    {
        return code;
    }

    public void setCode(
        final int code )
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

    public int isPresent()
    {
        return present;
    }

    public int getPresent()
    {
        return present;
    }

    public void setPresent(
        final int present )
    {
        this.present = present;
    }

}
