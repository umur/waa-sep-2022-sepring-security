package henry.springsecurity.service.impl;

import henry.springsecurity.entity.OffensiveWordsHistory;
import henry.springsecurity.repo.OffensiveWordsHistoryRepo;
import henry.springsecurity.service.OffensiveWordsHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OffensiveWordsHistoryServiceImpl implements OffensiveWordsHistoryService {
    private final OffensiveWordsHistoryRepo offensiveWordsHistoryRepo;

    @Override
    @Transactional
    public void save(OffensiveWordsHistory offensiveWordsHistory) {
        offensiveWordsHistoryRepo.save(offensiveWordsHistory);
    }

    @Override
    public Set<OffensiveWordsHistory> findByLast30Min(int userId) {
        return offensiveWordsHistoryRepo.findByAtLessThanEqualAndUser_Id(LocalDateTime.now().plusMinutes(30), userId);
    }
}
