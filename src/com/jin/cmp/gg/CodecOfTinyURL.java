package com.jin.cmp.gg;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Random;

/**
 * TinyURL is a URL shortening service where you enter a URL such as
 * https://leetcode.com/problems/design-tinyurl
 * and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service.
 * There is no restriction on how your encode/decode algorithm should work.
 * You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 *
 *
 *
 * http://blog.gainlo.co/index.php/2016/03/08/system-design-interview-question-create-tinyurl-system/
 */
public class CodecOfTinyURL {
    HashMap<String, String> maps = new HashMap<>();
    char[] base62 = {
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P',
            'Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f',
            'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
            'w','x','y','z','0','1','2','3','4','5','6','7','8','9'
    };
    Random rand = new Random();


    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String url = longUrl.toLowerCase();
        char[] code = new char[6];
        for (int i=0; i<6; i++) {
            code[i] = base62[rand.nextInt(62)];
        }
        String key = new String(code);
        maps.put(new String(key), longUrl);
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return maps.get(shortUrl.replace("http://tinyurl.com/", ""));

    }

    public static void main(String args[]) {
        CodecOfTinyURL codec = new CodecOfTinyURL();
        System.out.println("https://leetcode.com/problems/design-tinyurl => " + codec.encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println("https://leetcode.com/problems/design-tinyurl => " + codec.encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println("https://leetcode.com/problems/design-tinyurl => " + codec.encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println("https://leetcode.com/problems/design-tinyurl => " + codec.encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println("https://leetcode.com/problems/design-tinyurl => " + codec.encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println("https://leetcode.com/problems/design-tinyurl => " + codec.encode("https://leetcode.com/problems/design-tinyurl"));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));