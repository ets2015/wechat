package me.hao0.wechat.model.store;

import lombok.Data;
import me.hao0.wechat.model.store.Business.Content;

import java.util.List;

@Data
public class GetpoilistResult {
    /**
     * 门店列表
     */
    private List<Content> list;
    /**
     * 门店总数量
     */
    private Integer total_count;
}
