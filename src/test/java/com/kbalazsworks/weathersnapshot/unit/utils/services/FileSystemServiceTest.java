package com.kbalazsworks.weathersnapshot.unit.utils.services;

import com.kbalazsworks.weathersnapshot.MockFactory;
import com.kbalazsworks.weathersnapshot.unit.AbstractUnitTest;
import com.kbalazsworks.weathersnapshot.utils.factories.FileFactory;
import com.kbalazsworks.weathersnapshot.utils.factories.Slf4jLoggerFactory;
import com.kbalazsworks.weathersnapshot.utils.services.FileSystemService;
import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

public class FileSystemServiceTest extends AbstractUnitTest
{
    @Autowired
    FileSystemService fileSystemService;

    @Autowired
    FileFactory fileFactory;

    @Test
    public void getFolderFileListWithIgnoreList_folderNotFound_throwsNotDirectoryException()
    {
        // Arrange
        String            badPath              = "/qwe/123/ert";
        ArrayList<String> emptyIgnoreList      = new ArrayList<>();
        String            expectedErrorMessage = "Folder path is not exists or file.";

        // Act - Assert
        assertThatThrownBy(() -> fileSystemService.getFolderFileListWithIgnoreList(badPath, emptyIgnoreList))
            .isInstanceOf(NotDirectoryException.class)
            .hasMessage(expectedErrorMessage);
    }

    @Test
    public void getFolderFileListWithIgnoreList_filteredList_perfect()
    throws FileNotFoundException, NotDirectoryException
    {
        // Arrange
        String            dummyPath  = "/qwe/asd/zxc";
        ArrayList<String> ignoreList = new ArrayList<>();
        ignoreList.add("2.txt");
        ignoreList.add("3.txt");
        int    expectedResultNumber = 2;
        String expectedResultName1  = "1.txt";
        String expectedResultName2  = "4.txt";

        File mockFolderFile1 = Mockito.mock(File.class);
        when(mockFolderFile1.getName()).thenReturn("1.txt");

        File mockFolderFile2 = Mockito.mock(File.class);
        when(mockFolderFile2.getName()).thenReturn("2.txt");

        File mockFolderFile3 = Mockito.mock(File.class);
        when(mockFolderFile3.getName()).thenReturn("3.txt");

        File mockFolderFile4 = Mockito.mock(File.class);
        when(mockFolderFile4.getName()).thenReturn("4.txt");

        File[] folderFiles = new File[]{mockFolderFile1, mockFolderFile2, mockFolderFile3, mockFolderFile4};

        File mockDirectory = Mockito.mock(File.class);
        when(mockDirectory.exists()).thenReturn(true);
        when(mockDirectory.isDirectory()).thenReturn(true);
        when(mockDirectory.listFiles()).thenReturn(folderFiles);

        FileFactory mockFileFactory = Mockito.mock(FileFactory.class);
        when(mockFileFactory.create(dummyPath)).thenReturn(mockDirectory);

        fileSystemService.setFileFactory(mockFileFactory);

        // Act - Assert
        ArrayList<File> response = fileSystemService.getFolderFileListWithIgnoreList(dummyPath, ignoreList);

        // Assert
        assertAll(
            () -> assertThat(response.size()).isEqualTo(expectedResultNumber),
            () -> assertThat(response.get(0).getName()).isEqualTo(expectedResultName1),
            () -> assertThat(response.get(1).getName()).isEqualTo(expectedResultName2)
        );
    }

    @Test
    public void deleteFromFolderByIgnoreList_perfect_perfect() throws NotDirectoryException, FileNotFoundException
    {
        // Arrange
        String            dummyPath  = "/qwe/asd/zxc";
        ArrayList<String> ignoreList = new ArrayList<>();
        ignoreList.add("2.txt");
        ignoreList.add("3.txt");

        File mockFolderFile1 = Mockito.mock(File.class);
        when(mockFolderFile1.getName()).thenReturn("1.txt");

        File mockFolderFile4 = Mockito.mock(File.class);
        when(mockFolderFile4.getName()).thenReturn("4.txt");

        ArrayList<File> folderFiles = new ArrayList<>();
        folderFiles.add(mockFolderFile1);
        folderFiles.add(mockFolderFile4);

        FileSystemService spyFileSystemService = Mockito.spy(FileSystemService.class);

        doReturn(folderFiles)
            .when(spyFileSystemService)
            .getFolderFileListWithIgnoreList(Mockito.any(), Mockito.any());

        // Act
        spyFileSystemService.deleteFromFolderByIgnoreList(dummyPath, ignoreList);

        // Assert
        assertAll(
            () -> verify(mockFolderFile1, times(1)).delete(),
            () -> verify(mockFolderFile4, times(1)).delete()
        );
    }

    @Test
    public void deleteFromFolderByIgnoreList_getFolderFileListWithIgnoreListThrowsException_logTheError()
    throws NotDirectoryException, FileNotFoundException
    {
        // Arrange
        String            expectedErrorMessage = "No files found in folder: /qwe/asd/zxc";
        String            dummyPath            = "/qwe/asd/zxc";
        ArrayList<String> ignoreList           = new ArrayList<>();
        ArrayList<File>   folderFiles          = new ArrayList<>();

        FileSystemService spyFileSystemService = Mockito.spy(FileSystemService.class);
        doThrow(FileNotFoundException.class)
            .when(spyFileSystemService)
            .getFolderFileListWithIgnoreList(Mockito.any(), Mockito.any());

        Logger mockLogger             = Mockito.mock(Logger.class);
        Slf4jLoggerFactory mockSlf4jLoggerFactory = MockFactory.getMockSlf4jLoggerFactory(mockLogger);

        spyFileSystemService.setSlf4jLoggerFactory(mockSlf4jLoggerFactory);

        // Act
        spyFileSystemService.deleteFromFolderByIgnoreList(dummyPath, ignoreList);

        // Assert
        Mockito.verify(mockLogger, Mockito.times(1)).warn(expectedErrorMessage);
    }

    @Test
    public void deleteFromFolderByIgnoreList_callWithLogDeletedFiles_perfect()
    throws NotDirectoryException, FileNotFoundException
    {
        // Arrange
        String            expectedFirstLog  = "File deleted: /qwe/asd/zxc/1.txt - successful: true";
        String            expectedSecondLog = "File deleted: /qwe/asd/zxc/4.txt - successful: false";
        String            dummyPath         = "/qwe/asd/zxc";
        ArrayList<String> ignoreList        = new ArrayList<>();
        ignoreList.add("2.txt");
        ignoreList.add("3.txt");

        File mockFolderFile1 = Mockito.mock(File.class);
        when(mockFolderFile1.getPath()).thenReturn("/qwe/asd/zxc");
        when(mockFolderFile1.getName()).thenReturn("1.txt");
        when(mockFolderFile1.delete()).thenReturn(true);

        File mockFolderFile4 = Mockito.mock(File.class);
        when(mockFolderFile4.getPath()).thenReturn("/qwe/asd/zxc");
        when(mockFolderFile4.getName()).thenReturn("4.txt");
        when(mockFolderFile4.delete()).thenReturn(false);

        ArrayList<File> folderFiles = new ArrayList<>();
        folderFiles.add(mockFolderFile1);
        folderFiles.add(mockFolderFile4);

        FileSystemService spyFileSystemService = Mockito.spy(FileSystemService.class);

        doReturn(folderFiles)
            .when(spyFileSystemService)
            .getFolderFileListWithIgnoreList(Mockito.any(), Mockito.any());

        Logger mockLogger             = Mockito.mock(Logger.class);
        Slf4jLoggerFactory mockSlf4jLoggerFactory = MockFactory.getMockSlf4jLoggerFactory(mockLogger);

        spyFileSystemService.setSlf4jLoggerFactory(mockSlf4jLoggerFactory);

        // Act
        spyFileSystemService.deleteFromFolderByIgnoreList(dummyPath, ignoreList, true);

        // Assert
        assertAll(
            () -> verify(mockFolderFile1, times(1)).delete(),
            () -> verify(mockFolderFile4, times(1)).delete(),
            () -> verify(mockLogger, times(1)).info(expectedFirstLog),
            () -> verify(mockLogger, times(1)).info(expectedSecondLog)
        );
    }
}
