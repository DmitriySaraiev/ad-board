package com.saraiev.adboard.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedData<T> {

    @JsonProperty("items")
    private List<T> items;

    @JsonProperty("current_page")
    private Long currentPage;
    @JsonProperty("total_pages")
    private Long totalPages;
    @JsonProperty("total_items")
    private Long totalItems;

    public static <T> PagedData<T> create(BasePagedRequest request, List<T> list, Long maxCount) {
        PagedData<T> pagedData = new PagedData<>();
        pagedData.setItems(list);
        pagedData.setTotalItems(maxCount);
        pagedData.setCurrentPage(request.getPage());
        if ((double) maxCount % (double) request.getSize() != 0) {
            pagedData.setTotalPages((maxCount / request.getSize()) + 1);
        } else {
            pagedData.setTotalPages((maxCount / request.getSize()));
        }
        return pagedData;
    }
}
