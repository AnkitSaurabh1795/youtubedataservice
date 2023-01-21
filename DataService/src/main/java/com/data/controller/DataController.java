package com.data.controller;

import com.data.service.GetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/video/data")
public class DataController {
    private final GetDataService getDataService;
    @GetMapping("/find/{pageSize}/{pageNumber}")
    List<String> findData(@PathVariable Integer pageSize, @PathVariable Integer pageNumber){
        return getDataService.getData(pageSize, pageNumber);
    }

    @GetMapping("search")
    List<String> searchData(@RequestParam(value = "query")String searchQuery){
        return getDataService.searchData(searchQuery);
    }

}
