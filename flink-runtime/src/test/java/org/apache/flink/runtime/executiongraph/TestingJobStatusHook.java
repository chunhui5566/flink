/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.executiongraph;

import org.apache.flink.api.common.JobID;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/** {@link JobStatusHook} implementation for testing purposes. */
public class TestingJobStatusHook implements JobStatusHook {

    private Consumer<JobID> onCreatedConsumer = (jobID) -> {};
    private Consumer<JobID> onFinishedConsumer = (jobID) -> {};
    private BiConsumer<JobID, Throwable> onFailedConsumer = (jobID, throwable) -> {};
    private Consumer<JobID> onCanceledConsumer = (jobID) -> {};

    public void setOnCreatedConsumer(Consumer<JobID> onCreatedConsumer) {
        this.onCreatedConsumer = onCreatedConsumer;
    }

    public void setOnFinishedConsumer(Consumer<JobID> onFinishedConsumer) {
        this.onFinishedConsumer = onFinishedConsumer;
    }

    public void setOnFailedConsumer(BiConsumer<JobID, Throwable> onFailedConsumer) {
        this.onFailedConsumer = onFailedConsumer;
    }

    public void setOnCanceledConsumer(Consumer<JobID> onCanceledConsumer) {
        this.onCanceledConsumer = onCanceledConsumer;
    }

    @Override
    public void onCreated(JobID jobId) {
        onCreatedConsumer.accept(jobId);
    }

    @Override
    public void onFinished(JobID jobId) {
        onFinishedConsumer.accept(jobId);
    }

    @Override
    public void onFailed(JobID jobId, Throwable throwable) {
        onFailedConsumer.accept(jobId, throwable);
    }

    @Override
    public void onCanceled(JobID jobId) {
        onCanceledConsumer.accept(jobId);
    }
}
