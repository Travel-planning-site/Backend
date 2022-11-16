package com.CapStone.Backend.Repository;

import com.CapStone.Backend.Entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelInfoRepository extends JpaRepository<Travel, Long>  {
    List<Travel> findAllByInfoIdx(int infoIdx);
}
