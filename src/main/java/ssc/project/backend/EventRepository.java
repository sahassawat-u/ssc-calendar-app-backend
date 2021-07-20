package ssc.project.backend;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Events, Long>{
    Events findFirstByUsername(String username);
//    void deleteById(int id);
}