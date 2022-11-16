package com.CapStone.Backend.Service;

import com.CapStone.Backend.Dto.BasicInfoRequest;
import com.CapStone.Backend.Dto.TravleInfoRequest;
import com.CapStone.Backend.Entity.Info;
import com.CapStone.Backend.Entity.Travle;
import com.CapStone.Backend.Repository.InfoRepository;
import com.CapStone.Backend.Repository.TravleInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravleInfoService {
    private final TravleInfoRepository travleInfoRepository;
    public void saveTravleInfo(TravleInfoRequest request) {
        Travle travle = new Travle();
        travle.setInfoIdx(request.getInfoIdx());
        travle.setStartPlaceImg(request.getStartPlaceImg());
        travle.setArrivalPlaceImg(request.getArrivalPlaceImg());
        travle.setStartPlace(request.getStartPlace());
        travle.setArrivalPlace(request.getArrivalPlace());
        travle.setCost(request.getCost());
        travle.setStartTime(request.getStartTime());
        travle.setArriveTime(request.getArriveTime());
        travle.setTransportation(request.getTransportation());
        travle.setTotalTime(request.getTotalTime());
        travle.setMemo(request.getMemo());
        travle.setUserId(request.getUserId());
        travleInfoRepository.save(travle);
    }
}
