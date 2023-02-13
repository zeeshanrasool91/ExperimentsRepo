package com.example.myapplication;

import java.nio.charset.StandardCharsets;
import java.util.zip.*;
import java.io.UnsupportedEncodingException;

class DecompressionUsingInflator {
    public static void main()
            throws UnsupportedEncodingException,
            DataFormatException {

        Deflater def = new Deflater();
        String str = "ABCDEF";
        StringBuilder finalStr = new StringBuilder();
        for (int i = 0; i < 3; i++)
            finalStr.append(str);
        def.setInput(str.getBytes(StandardCharsets.UTF_8));
        def.finish();


        byte[] compString = new byte[1024];


        int Compsize = def.deflate(compString);
        def.end();

        // This is the end of compression. Now Inflater is used to get back the original string data.

        // Inflater Class object is created
        Inflater inf = new Inflater();

        // the compString is set as input to be decompressed
        inf.setInput(compString);

        // byte array set for decompressed string
        byte[] orgString = new byte[1024];

        // decompress the string data
        int orgSize = inf.inflate(orgString, 0, 18);

        // showing output of inflater and deflater
        System.out.println("Compressed string data: "
                + new String(compString));
        System.out.println("Decompressed string data: "
                + new String(orgString, StandardCharsets.UTF_8));

        inf.end();
    }
}