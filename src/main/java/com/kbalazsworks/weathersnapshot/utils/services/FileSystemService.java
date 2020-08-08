package com.kbalazsworks.weathersnapshot.utils.services;

import com.kbalazsworks.weathersnapshot.utils.factories.FileFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;

@Service
public class FileSystemService
{
    private static Logger logger;

    private FileFactory fileFactory;

    @Autowired
    public void setSlf4jLoggerFactory(Slf4jLoggerFactory slf4jLoggerFactory)
    {
        FileSystemService.logger = slf4jLoggerFactory.create(FileSystemService.class);
    }

    @Autowired
    public void setFileFactory(FileFactory fileFactory)
    {
        this.fileFactory = fileFactory;
    }

    public ArrayList<File> getFolderFileListWithIgnoreList(String folderPath, ArrayList<String> ignoreList)
    throws FileNotFoundException, NotDirectoryException
    {
        File folder = fileFactory.create(folderPath);

        if (null == folder || !folder.exists() || !folder.isDirectory())
        {
            throw new NotDirectoryException("Folder path is not exists or file.");
        }

        File[]          folderFiles        = fileFactory.create(folderPath).listFiles();
        ArrayList<File> returnIgnoredFiles = new ArrayList<>();

        if (null == folderFiles)
        {
            throw new FileNotFoundException("No files found.");
        }

        for (File file : folderFiles)
        {
            if (!ignoreList.contains(file.getName()))
            {
                returnIgnoredFiles.add(file);
            }
        }

        return returnIgnoredFiles;
    }

    public void deleteFromFolderByIgnoreList(String folder, ArrayList<String> keepFiles) throws NotDirectoryException
    {
        deleteFromFolderByIgnoreList(folder, keepFiles, false);
    }

    public void deleteFromFolderByIgnoreList(String folder, ArrayList<String> keepFiles, boolean writeLog)
    throws NotDirectoryException
    {
        ArrayList<File> folderFiles;

        try
        {
            folderFiles = getFolderFileListWithIgnoreList(folder, keepFiles);
        }
        catch (FileNotFoundException e)
        {
            logger.warn("No files found in folder: " + folder);

            return;
        }

        for (File folderFileToDelete : folderFiles)
        {
            boolean isDeleted = folderFileToDelete.delete();
            if (writeLog)
            {
                logger.info(
                    "File deleted: " + folderFileToDelete.getPath() + "/" + folderFileToDelete.getName()
                    + " - successful: " + isDeleted
                );
            }
        }
    }
}
