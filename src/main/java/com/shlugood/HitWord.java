package com.shlugood;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HitWord {
    private final int beginIndex;
    private final int endIndex;
    private double frequency;
}
