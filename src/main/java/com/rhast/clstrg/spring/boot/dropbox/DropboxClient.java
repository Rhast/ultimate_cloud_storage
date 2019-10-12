package com.rhast.clstrg.spring.boot.dropbox;

import java.util.List;
import java.util.stream.Collectors;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

/**
 * @author lermontov-w
 */
public class DropboxClient {

    private static final String DROPBOX_APP_NAME = "ultimate_cloud_storage";
    private static final String ACCESS_TOKEN = "ORBU4kNIV6sAAAAAAAAhkniPMNlHOPMUzgMDHqAhmfDlxLaQsgqyDP6O503_jTpL";

    private static final DbxRequestConfig DROPBOX_CONFIG = new DbxRequestConfig(DROPBOX_APP_NAME);


    private final DbxClientV2 dropboxClient;

    public DropboxClient() {
        this.dropboxClient = new DbxClientV2(DROPBOX_CONFIG, ACCESS_TOKEN);
    }

    public List<String> listFiles(String root) throws DbxException {
        return listFolder(root).getEntries().stream()
                .map(Metadata::getPathDisplay)
                .collect(Collectors.toList());
    }

    private ListFolderResult listFolder(String root) throws DbxException {
        return dropboxClient.files().listFolder(root);
    }

    public static void main(String[] args) {

    }
}
