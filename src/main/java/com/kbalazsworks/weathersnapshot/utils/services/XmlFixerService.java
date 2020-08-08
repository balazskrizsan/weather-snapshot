package com.kbalazsworks.weathersnapshot.utils.services;

import org.springframework.stereotype.Service;

import java.io.*;

//@todo: test with broken XML (0xC in the file)
@Service
public class XmlFixerService
{
    public void fixBadFormat(String inputFileWithPath, String fixedFileWithPath)
    {
        BufferedReader bufferedReader = null;
        PrintWriter    printWriter    = null;

        try
        {
            bufferedReader = new BufferedReader(new FileReader(inputFileWithPath));
            printWriter = new PrintWriter(new FileWriter(fixedFileWithPath));

            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                printWriter.println(stripNonValidXMLCharacters(line));
            }

            bufferedReader.close();
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String stripNonValidXMLCharacters(String in)
    {
        if (in == null || ("".equals(in)))
        {
            return "";
        }

        StringBuffer out = new StringBuffer();
        char         current;

        for (int i = 0; i < in.length(); i++)
        {
            current = in.charAt(i);
            if (current != 0xC)
            {
                out.append(current);
            }
        }

        return out.toString();
    }
}
