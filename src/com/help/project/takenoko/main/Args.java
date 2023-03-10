package com.help.project.takenoko.main;

import com.beust.jcommander.Parameter;

public class Args {

    @Parameter(names = "--2thousands", description = "Used for delivery's script")
    private boolean twoThousands;

    @Parameter(names = "--demo", description = "Used for the oral")
    private boolean demo;

    @Parameter(names = "--csv", description = "Used for csv generation")
    private boolean csv;

    public boolean isTwoThousands() {
        return twoThousands;
    }

    public boolean isDemo() {
        return demo;
    }

    public boolean isCsv() {
        return csv;
    }
}
