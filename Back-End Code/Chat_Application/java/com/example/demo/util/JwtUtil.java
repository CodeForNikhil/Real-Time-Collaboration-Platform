package com.example.demo.util;

public class JwtUtil {
	
	private JwtUtil() {}

    public static String sanitizeToken(String header) {
        return header == null ? null : header.replace("Bearer ", "");
    }


}
