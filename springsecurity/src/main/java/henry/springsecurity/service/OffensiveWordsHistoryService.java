package henry.springsecurity.service;

import henry.springsecurity.entity.OffensiveWordsHistory;

import java.util.Set;

public interface OffensiveWordsHistoryService {
    void save(OffensiveWordsHistory offensiveWordsHistory);
    Set<OffensiveWordsHistory> findByLast30Min(int userId);
}
