package com.store.catalog.search;



import com.store.catalog.model.nosql.SearchableItem;

import java.util.List;

public interface ItemSearchDao {


    public String create(SearchableItem searchableItem) throws Exception ;


    public SearchableItem update(SearchableItem searchableItem) throws Exception ;


    public SearchableItem get(String id) throws Exception;


    public boolean delete(String id) throws Exception ;


    /**
     *
     * @param keyword
     * @param from
     * @param page
     * @return
     * @throws Exception
     */
    public List<SearchableItem> matchItems(String keyword, Integer from, Integer page) throws Exception;


    /**
     *
     * @param keyword
     * @param from
     * @param page
     * @return
     * @throws Exception
     */
    public List<SearchableItem> matchPhrasePrefixItems(String keyword, Integer from, Integer page) throws Exception;


    public Long countAll() throws Exception;

}
