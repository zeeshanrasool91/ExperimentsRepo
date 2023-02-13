package com.example.myapplication;

import java.nio.charset.StandardCharsets;
import java.util.zip.*;
import java.io.UnsupportedEncodingException;

class CompressionUsingDeflator {
    public static void main()
            throws UnsupportedEncodingException {
        // deflater object is created
        Deflater def = new Deflater();
        // get the string to be compressed
        String str = "ABCDEF";
        StringBuilder finalStr = new StringBuilder();
        // This loop will create a final strig to be compressed by                              repeating the str 3 times generating a repeating pattern
        for (int i = 0; i < 3; i++)
            finalStr.append(str);
        // set the input for deflator by converting it into bytes
        def.setInput(finalStr.toString().getBytes(StandardCharsets.UTF_8));
        // finish.The finished() function in the Inflater class returns true when it reaches the end of compression data stream.
        def.finish();
        // output string data in bytes
        byte[] compString = new byte[1024];
        // compressed string data will be stored in compString, offset is set to 3 and maximum size of compressed string is 13.
        int compSize = def.deflate(compString, 3, 13, Deflater.FULL_FLUSH);
        // Final compressed String
        System.out.println("Compressed String :" + new String(compString) + "\n Size :" + compSize);
        // original String is printed for reference
        System.out.println("Original String :" + finalStr + "\n Size :" + finalStr.length());
        // object end
        def.end();
    }
}
