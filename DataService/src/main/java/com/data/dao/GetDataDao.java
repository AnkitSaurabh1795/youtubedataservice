package com.data.dao;

import com.data.util.MongoDbHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GetDataDao {
    private final MongoDbHelper dbHelper;

    public List<String> getData(int pageSize, int pageNumber){
        int skip = pageSize * pageNumber;
        return dbHelper.getData(pageSize,skip);
    }

    public List<String> searchData(String searchQuery){
        return dbHelper.searchData(searchQuery);
    }
}
