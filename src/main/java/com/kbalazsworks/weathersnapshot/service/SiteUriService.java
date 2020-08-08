package com.kbalazsworks.weathersnapshot.service;

import com.kbalazsworks.weathersnapshot.repository.SiteUriRepository;
import com.kbalazsworks.weathersnapshot.repository.SiteUrisWithDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SiteUriService {
    private SiteUriRepository siteUriRepository;

    @Autowired
    public void setSiteUriRepository(SiteUriRepository siteUriRepository) {
        this.siteUriRepository = siteUriRepository;
    }

    public ArrayList<SiteUrisWithDomain> searchWithDomain() {
        return siteUriRepository.searchWithDomain();
    }
}
