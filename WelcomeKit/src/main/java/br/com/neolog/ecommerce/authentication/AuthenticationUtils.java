package br.com.neolog.ecommerce.authentication;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class AuthenticationUtils
{

    public static String encryptPassword(
        final String password )
    {
        final Hasher hasher = Hashing.sha512().newHasher();
        hasher.putString( password, StandardCharsets.UTF_8 );
        return hasher.hash().toString();
    }

    public static String generateToken()
    {
        return "" + Double.valueOf( Math.random() * 1000 ).longValue() + System.currentTimeMillis();
    }

}
