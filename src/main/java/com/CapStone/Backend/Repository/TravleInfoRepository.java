package com.CapStone.Backend.Repository;

import com.CapStone.Backend.Entity.Travle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravleInfoRepository extends JpaRepository<Travle, Long>  {
    List<Travle> findAllByInfoIdx(int infoIdx);
}
