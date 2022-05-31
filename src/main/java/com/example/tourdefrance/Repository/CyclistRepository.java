package com.example.tourdefrance.Repository;

import com.example.tourdefrance.Entity.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CyclistRepository extends JpaRepository <Cyclist, Integer>{

    List<Cyclist> findCyclistByTeam_Name(String name);

}
