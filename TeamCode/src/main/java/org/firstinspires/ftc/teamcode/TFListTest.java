package org.firstinspires.ftc.teamcode;

import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Duplicates1 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 3, 1, 2, 3, 3, 3);
        Log.i("MyApp","I am here");
        System.out.println("Count all with frequency");
        Set<Integer> set = new HashSet<Integer>(list);
        for (Integer r : set) {
            System.out.println(r + ": " + Collections.frequency(list, r));
        }

    }
}

public class TFListTest {
}
