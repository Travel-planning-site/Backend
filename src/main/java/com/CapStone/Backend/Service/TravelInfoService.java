package com.CapStone.Backend.Service;

import com.CapStone.Backend.Dto.TravelInfoRequest;
import com.CapStone.Backend.Entity.Travel;
import com.CapStone.Backend.Repository.TravelInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelInfoService {
    private final TravelInfoRepository travelInfoRepository;
    public void saveTravleInfo(TravelInfoRequest request) {
        Travel travel = new Travel();
        travel.setInfoIdx(request.getInfoIdx());
        travel.setStartPlaceImg(request.getStartPlaceImg());
        travel.setArrivalPlaceImg(request.getArrivalPlaceImg());
        travel.setStartPlace(request.getStartPlace());
        travel.setArrivalPlace(request.getArrivalPlace());
        travel.setCost(request.getCost());
        travel.setStartTime(request.getStartTime());
        travel.setArriveTime(request.getArriveTime());
        travel.setTransportation(request.getTransportation());
        travel.setTotalTime(request.getTotalTime());
        travel.setMemo(request.getMemo());
        travel.setUserId(request.getUserId());
        travelInfoRepository.save(travel);
    }
}
