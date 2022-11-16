package com.CapStone.Backend.Service;

import com.CapStone.Backend.Dto.BasicInfoRequest;
import com.CapStone.Backend.Dto.BasicInfoResponse;
import com.CapStone.Backend.Entity.Info;
import com.CapStone.Backend.Entity.Travle;
import com.CapStone.Backend.Repository.InfoRepository;
import com.CapStone.Backend.Repository.TravleInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicInfoService {
    private final InfoRepository infoRepository;
    private final TravleInfoRepository travleInfoRepository;
    public int saveInfo(BasicInfoRequest basicInfo) {
        Info info = new Info();
        info.setTitle(basicInfo.getTitle());
        info.setPlace(basicInfo.getPlace());
        info.setPeople(basicInfo.getPeople());
        info.setPeriod(basicInfo.getPeriod());
        info.setUserId(basicInfo.getUserId());
        info.setMemo(basicInfo.getMemo());
        infoRepository.save(info);
        System.out.println("id: " + info.getIdx());

        return info.getIdx();
    }

    public List<BasicInfoResponse> selectPlan() {
        List<BasicInfoResponse> respList = new ArrayList<>();
        List<Info> infos = infoRepository.findAll();

        for (Info info : infos) {
            List<Travle> travles = travleInfoRepository.findAllByInfoIdx(info.getIdx());
            BasicInfoResponse basicInfo = new BasicInfoResponse(info, travles);
            respList.add(basicInfo);
        }
        return respList;
    }
}
