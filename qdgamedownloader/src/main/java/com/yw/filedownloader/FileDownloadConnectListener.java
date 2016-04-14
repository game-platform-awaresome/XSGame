/*
 * Copyright (c) 2015 LingoChamp Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yw.filedownloader;

import com.yw.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.yw.filedownloader.event.IDownloadEvent;
import com.yw.filedownloader.event.IDownloadListener;
import com.yw.filedownloader.services.FileDownloadService;

/**
 * Created by Jacksgong on 1/10/16.
 * <p/>
 * For listening whether the service establishes connection or disconnected.
 *
 * @see com.yw.filedownloader.services.BaseFileServiceUIGuard#releaseConnect(boolean)
 */
public abstract class FileDownloadConnectListener extends IDownloadListener {

    public FileDownloadConnectListener() {
    }

    @Override
    public boolean callback(IDownloadEvent event) {
        if (event instanceof DownloadServiceConnectChangedEvent) {
            final DownloadServiceConnectChangedEvent connectChangedEvent
                    = (DownloadServiceConnectChangedEvent) event;
            if (connectChangedEvent.isSuchService(FileDownloadService.class)
                    && connectChangedEvent.getStatus()
                    == DownloadServiceConnectChangedEvent.ConnectStatus.connected) {
                connected();
            } else {
                disconnected();
            }
        }
        return false;
    }

    /**
     * connected file download service
     */
    public abstract void connected();

    /**
     * disconnected file download service
     */
    public abstract void disconnected();

}
