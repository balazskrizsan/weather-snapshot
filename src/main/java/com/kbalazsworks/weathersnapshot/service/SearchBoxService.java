package com.kbalazsworks.weathersnapshot.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchBoxService
{
    private JestClient jestClient;
    private Logger logger = LoggerFactory.getLogger(SearchBoxService.class);

    @Autowired
    public void setJestClient(JestClient jestClient)
    {
        this.jestClient = jestClient;
    }

    public void createIndex(String indexName)
    {
        try
        {
            JestResult result = jestClient.execute(new IndicesExists.Builder(indexName).build());
            if (result.getResponseCode() != 200)
            {
                jestClient.execute(new CreateIndex.Builder(indexName).build());
                logger.error(result.getErrorMessage(), result);
            }
            logger.info("Index created");
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
        }
    }

    public void deleteIndex(String indexName)
    {
        try
        {
            jestClient.execute(new DeleteIndex.Builder(indexName).build());
            logger.info("Index deleted");
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
    }

    public void createDocument(String index, JsonNode node)
    {
        try
        {
            jestClient.execute(new Index.Builder(node.toString()).index(index).type("_doc").build());
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
        }
    }
}
