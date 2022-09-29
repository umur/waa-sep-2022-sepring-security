package edu.miu.lab5.aspect;

import edu.miu.lab5.entity.Product;
import edu.miu.lab5.entity.User;
import edu.miu.lab5.entity.WaaOffensive;
import edu.miu.lab5.exception.BannedException;
import edu.miu.lab5.repository.UserRepository;
import edu.miu.lab5.repository.WaaOffensiveRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
@Transactional
public class WaaOffensiveWordsService {

    private WaaOffensiveRepository waaOffensiveRepository;
    private UserRepository userRepository;

    public WaaOffensiveWordsService(WaaOffensiveRepository repositroy, UserRepository userRepository) {
        this.waaOffensiveRepository = repositroy;
        this.userRepository = userRepository;
    }

    private static final int timeToBeBanned_InMin = 1;
    private static final int timeBetweenOffinsiveReq_InMin = 2;
    private static final int words_Count_To_Banned = 5;
    private static final List<String> offensiveWordsList = Arrays.asList(new String[]{"spring"});


    public void CheckWordForUser(String email, String word, Product body) throws Exception {

        var WaaOffensiveOptional = waaOffensiveRepository.findByUser_Email(email);
        if (WaaOffensiveOptional != null) {
            WaaOffensive model = WaaOffensiveOptional;

            //Will Stop if under Banned
            CheckIfUserUndedBanned(model);

            OffinsiveWordLogicForExistingRecord(model, word,body);

        } else {

            if (checkIfWordIsOffensive(word,body)) {
                var user = userRepository.findByEmail(email);
                //New Record
                var record = new WaaOffensive();
                record.setUser(user);
                record.setWordsCount(1);
                record.setLastRequestDateTime(new Date());
                waaOffensiveRepository.save(record);
            }

        }
    }

    private void OffinsiveWordLogicForExistingRecord(WaaOffensive model, String word,Product body) {
        boolean isCurrentIsOffinsive = checkIfWordIsOffensive(word,body);
        //IF not offinsive let it go
        if (!isCurrentIsOffinsive)
            return;

        Date date = new Date(model.getLastRequestDateTime().getTime());
        date.setMinutes(date.getMinutes() + timeBetweenOffinsiveReq_InMin);
        if(!date.after(new Date())){
            //Treat as first new Offinsive word
            SetTheCounterToOneAndUnblock(model);
            return;
        }
        //ELse continue on the old

        if (model.isUnderBlocked()) {
            SetTheCounterToOneAndUnblock(model);
            return;
        }
//        else if (model.isUnderBlocked()) {
//            ResetTheCounterAndUnBlock(model);
//            return;
//        }
        else {

            if (model.getWordsCount() + 1 == words_Count_To_Banned) {
                //Put under Block
                PutUserUnderBlock(model);
                return;
            }

            IncreamantTheCounter(model);
            return;

        }
    }

    private void PutUserUnderBlock(WaaOffensive model) {
        model.setWordsCount(words_Count_To_Banned);
        model.setUnderBlocked(true);
        model.setBannedDateTime(new Date());

        UpdateWaaOffensiveRecord(model);
    }

    private void IncreamantTheCounter(WaaOffensive model) {
        model.setWordsCount(model.getWordsCount() + 1);

        UpdateWaaOffensiveRecord(model);
    }

    private void UpdateWaaOffensiveRecord(WaaOffensive model) {
        model.setLastRequestDateTime(new Date());
        waaOffensiveRepository.save(model);
    }

    private void ResetTheCounterAndUnBlock(WaaOffensive model) {
        model.setWordsCount(0);
        model.setUnderBlocked(false);

        UpdateWaaOffensiveRecord(model);
    }

    private void SetTheCounterToOneAndUnblock(WaaOffensive model) {

        model.setWordsCount(1);
        model.setUnderBlocked(false);

        UpdateWaaOffensiveRecord(model);
    }

    private boolean checkIfWordIsOffensive(String word,Product body) {
        if (offensiveWordsList.contains(word)) {

            body.setName(filterOffinsiveWord(body.getName()));
            return true;
        }
        return false;
    }

    private String filterOffinsiveWord(String name) {
        int beginIndex=name.length()-3<0?0:name.length()-3;
        int endIndex=beginIndex==0?0:name.length();
        return "**.."+name.substring(beginIndex,endIndex);
    }

    private void CheckIfUserUndedBanned(WaaOffensive model) throws Exception {
        if (!model.isUnderBlocked()) return;

        Date date = new Date(model.getBannedDateTime().getTime());
        date.setMinutes(date.getMinutes() + timeToBeBanned_InMin);

        if (date.after(new Date())) {
            throw new BannedException("You still in the banned duration");
        }

    }


}
