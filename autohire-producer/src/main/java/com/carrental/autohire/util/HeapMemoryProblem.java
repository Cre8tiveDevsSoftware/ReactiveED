package com.carrental.autohire.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HeapMemoryProblem {
    public static void main(String[] args) {
        List<int[]> listOfArrays = new ArrayList<>();

        try {
            while (true) {
                int[] largeArray = new int[1000000];
                listOfArrays.add(largeArray);
            }
        } catch (OutOfMemoryError e) {
           log.error("Out of memory exception ",e);
            e.printStackTrace();
        }
    }
}
