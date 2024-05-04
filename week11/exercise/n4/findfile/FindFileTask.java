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
    private static final int SEGMENTS = 4;
    private final int segmentNo;

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

    }

    @Override
    public void compute() {
        //sequentiell searching
        if (segmentNo >= 0){
            FindFile.findFile(regex, dir);
            if (result.compareAndSet(null, "Result Found")){
                this.quietlyCompleteRoot();
            }
        }
        //generating parallel tasks
        else {
            this.addToPendingCount(FindFileTask.SEGMENTS);
            for (int i = 0; i < FindFileTask.SEGMENTS+1; i++) {
                new FindFileTask(this, regex, dir, result, i).fork();
            }
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
