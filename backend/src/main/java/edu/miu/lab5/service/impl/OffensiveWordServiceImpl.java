package edu.miu.lab5.service.impl;

import edu.miu.lab5.entity.OffensiveWordAudit;
import edu.miu.lab5.repo.OffensiveWordsAuditRepo;
import edu.miu.lab5.service.OffensiveWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OffensiveWordServiceImpl implements OffensiveWordService {

    private final OffensiveWordsAuditRepo offensiveWordsAuditRepo;

    @Override
    @Transactional
    public void save(OffensiveWordAudit offensiveWordAudit) {
        offensiveWordsAuditRepo.save(offensiveWordAudit);
    }

    @Override
    public Set<OffensiveWordAudit> findByLast30Minutes(int userId) {
        return offensiveWordsAuditRepo.findByCreatedAtLessThanEqualAndUserId(LocalDateTime.now().plusMinutes(30), userId);
    }
}
