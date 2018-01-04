package com.guxiang.search.service;


import com.guxiang.common.pojo.SearchResult;

/**
 * SearchService
 *
 * @author guxiang
 * @date 2017/12/29
 */
public interface SearchService {
    SearchResult search(String keyword, int page, int rows)  throws Exception;
}
