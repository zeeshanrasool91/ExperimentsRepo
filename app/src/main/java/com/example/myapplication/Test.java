package com.example.myapplication;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class Test {

    public static void main(String[] args) {

        ExtensionsKt.executeAfterDelay(2, 300L, 3000L, new Function2<Integer, Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer, Integer integer2) {
                return Unit.INSTANCE;
            }
        });
    }
}
