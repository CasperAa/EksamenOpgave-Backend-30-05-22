package com.example.tourdefrance.Repository;
import com.example.tourdefrance.Entity.RaceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceDataRepository extends JpaRepository <RaceData, Integer>{
}
