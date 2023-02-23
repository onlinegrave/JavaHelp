package com.code_help.paul_plays;

public class ShortWordFilter implements Filter {
    @Override
    public boolean accept(Object o) {
        return o.toString().length() < 5;
    }
}
