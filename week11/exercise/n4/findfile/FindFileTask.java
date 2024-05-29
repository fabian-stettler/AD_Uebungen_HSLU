/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Uebungen_AD.week11.exercise.n4.findfile;

import Uebungen_AD.week11.n41.array.search.SearchTask;

import java.io.File;
import java.util.Arrays;
import java.util.SplittableRandom;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Codevorlage zu CountedCompleter für eine Dateisuche.
 */
@SuppressWarnings("serial")
public final class FindFileTask extends CountedCompleter<String> {

    private final String regex;
    private final File dir;
    private final AtomicReference<String> result;
    private static int SEGMENTS;
    private final int segmentNo;
    private File[] subdirectories;

    /**
     * Erzeugt eine File-Such-Aufgabe.
     *
     * @param regex Ausdruck der den Filenamen enthält.
     * @param dir Verzeichnis in dem das File gesucht wird.
     */
    public FindFileTask(String regex, File dir) {
        this(null, regex, dir, new AtomicReference<>(null), -1);
    }

    private FindFileTask(final CountedCompleter<?> parent, final String regex, final File dir,
            final AtomicReference<String> result, int segmentNo) {
        super(parent);
        this.regex = regex;
        this.dir = dir;
        this.result = result;
        this.segmentNo = segmentNo;
        File[] listedFiles = this.dir.listFiles();
        if (listedFiles != null) {              //only include directories in array listedFiles
            this.subdirectories = Arrays.stream(listedFiles)
                    .filter(File::isDirectory)
                    .toArray(File[]::new);
        } else {
            this.subdirectories = new File[0];  // Initialize as empty array to prevent NullPointerException
        }

        SEGMENTS = subdirectories.length;
    }

    @Override
    public void compute() {
        //sequentiell searching
        if (segmentNo >= 0){
            String resultCall = FindFile.findFile(regex, dir);
            result.compareAndSet(null, resultCall);
            //decrease counter
            if (resultCall != null){
                quietlyCompleteRoot();
            }
            else{
                tryComplete();
            }
        }
        //generating parallel tasks
        else {
            if (subdirectories != null && subdirectories.length > 0) {
                setPendingCount(subdirectories.length);  // Set pending count to the number of subdirectories
                for (File subdir : subdirectories) {
                    new FindFileTask(this, regex, subdir, result, 1).fork();
                }
            } else {
                // No subdirectories, check this directory
                tryComplete();  // Complete as there are no further subdivisions
            }
        }
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        if (this == getRoot()) {
            System.out.println(getRawResult());
        }
    }

    @Override
    public String getRawResult() {
        if (result != null) {
            return result.get();
        }
        return null;
    }
}
