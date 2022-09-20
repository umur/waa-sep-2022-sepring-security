package edu.miu.lab5.service;

import edu.miu.lab5.entity.OffensiveWordAudit;

import java.util.Set;

public interface OffensiveWordService {

    void save(OffensiveWordAudit offensiveWordAudit);

    Set<OffensiveWordAudit> findByLast30Minutes(int userId);
}
