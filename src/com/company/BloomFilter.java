package com.company;

import java.util.BitSet;

public class BloomFilter {
    public int filter_len;
    BitSet storage;

    public BloomFilter(int f_len) {
        filter_len = f_len;
        storage = new BitSet(filter_len);
    }


    public int hash1(String str1) {
        int index = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            index = (index * 17 + code) % filter_len ;
        }
        return index;
    }

    public int hash2(String str1) {
        int index = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = (int) str1.charAt(i);
            index = (index * 223 + code) % filter_len;
        }
        return index ;
    }

    public void add(String str1) {
        int first = hash1(str1);
        int second = hash2(str1);
        storage.set(first);
        storage.set(second);

    }

    public boolean isValue(String str1) {
        int first = hash1(str1);
        int second = hash2(str1);
        return storage.get(first) && storage.get(second);
    }
}