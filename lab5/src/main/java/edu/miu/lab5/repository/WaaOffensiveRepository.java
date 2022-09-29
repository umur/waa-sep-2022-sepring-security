package edu.miu.lab5.repository;


import edu.miu.lab5.entity.User;
import edu.miu.lab5.entity.WaaOffensive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaaOffensiveRepository extends CrudRepository<WaaOffensive,Integer> {

    WaaOffensive findByUser_Email(String email);
}
