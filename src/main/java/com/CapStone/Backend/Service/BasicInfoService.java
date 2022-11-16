package com.CapStone.Backend.Service;

import com.CapStone.Backend.Dto.BasicInfoRequest;
import com.CapStone.Backend.Entity.Info;
import com.CapStone.Backend.Repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasicInfoService {
    private final InfoRepository infoRepository;
    public void saveInfo(BasicInfoRequest basicInfo) {
        Info info = new Info();
        info.setTitle(basicInfo.getTitle());
        info.setPlace(basicInfo.getPlace());
        info.setPeople(basicInfo.getPeople());
        info.setPeriod(basicInfo.getPeriod());
        info.setUserId(basicInfo.getUserId());
        info.setMemo(basicInfo.getMemo());
        infoRepository.save(info);
    }
}
