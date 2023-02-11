package com.code_help.walrus;

import org.junit.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomSeed {
    @Test
    public void randomSeedTest() {
        Random random = new Random(0);

            assertEquals(random.nextInt(),-1155484576);
            assertEquals(random.nextInt(),-723955400);
            assertEquals(random.nextInt(),1033096058);
            assertEquals(random.nextInt(),-1690734402);
            assertEquals(random.nextInt(),-1557280266);
            assertEquals(random.nextInt(),1327362106);
            assertEquals(random.nextInt(),-1930858313);
            assertEquals(random.nextInt(),502539523);
            assertEquals(random.nextInt(),-1728529858);
            assertEquals(random.nextInt(),-938301587);

    }
    @Test
    public  void randomSeeTest2() {
        Random random = new Random(0);

            assertEquals(random.nextInt(),-1155484576);
            assertEquals(random.nextInt(),-723955400);
            assertEquals(random.nextInt(),1033096058);
            assertEquals(random.nextInt(),-1690734402);
            assertEquals(random.nextInt(),-1557280266);
            assertEquals(random.nextInt(),1327362106);
            assertEquals(random.nextInt(),-1930858313);
            assertEquals(random.nextInt(),502539523);
            assertEquals(random.nextInt(),-1728529858);
            assertEquals(random.nextInt(),-938301587);

    }
}
