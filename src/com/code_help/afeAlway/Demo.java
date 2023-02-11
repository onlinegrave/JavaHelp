package com.code_help.afeAlway;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Demo extends Super {
    @Before
    public void before() {
        System.out.println("BEFORE");
    }

    @Test
    public void test() {
        System.out.println("TEST");
    }

    @After
    public void after() {
        System.out.println("BEFORE");
    }


}

abstract  class Super {
    @Before
    public void before() {
        System.out.println("BEFORE SUPER");
    }

    @Test
    public void test() {
        System.out.println("TEST SUPER");
    }

    @After
    public void after() {
        System.out.println("BEFORE SUPER");
    }

}
