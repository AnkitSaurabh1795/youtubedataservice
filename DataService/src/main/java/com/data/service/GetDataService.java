package com.data.service;

import com.data.dao.GetDataDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDataService {
    private final GetDataDao getDataDao;
    public List<String> getData(Integer pageSize, Integer pageNumber){
        return getDataDao.getData(pageSize,pageNumber);
    }

    public List<String> searchData(String searchQuery){
        return getDataDao.searchData(searchQuery);
    }
}
