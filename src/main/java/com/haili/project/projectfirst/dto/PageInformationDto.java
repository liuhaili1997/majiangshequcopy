package com.haili.project.projectfirst.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by hailitortoise on 2020-03-27
 */
@Data
public class PageInformationDto {

    /**
     * 查询到聚合数据
     */
    private List<QuestionDto> questionDtoList;


    /**
     * 展示跳往前一页的按钮
     */
    private boolean showPrevious;

    /**
     * 展示回到第一页按钮
     */
    private boolean showFirstPage;

    /**
     * 展示下一页按钮
     */
    private boolean showNext;

    /**
     * 展示跳往最后一页按钮
     */
    private boolean showEndPage;

    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 展示页数
     */
    private List<Integer> pages = new ArrayList<>();

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 对数据的处理，实现前端页面的分页和数据的展示
     *
     * @param total       总数
     * @param currentPage 当前页数
     * @param pageSize    每页大小
     */
    public void setPageInformation(Integer total, Integer currentPage, Integer pageSize) {

        if (total % pageSize == 0) {
            totalPage = total / pageSize;
        } else {
            totalPage = total / pageSize + 1;
        }
        //健壮性
        if (currentPage < 1) {
            currentPage = 1;
        }
        //totalPage 总页数
        if (currentPage > totalPage) {
            currentPage =  totalPage;
        }
        this.currentPage = currentPage;
        pages.add(currentPage);
        switch (currentPage) {
            case 1:
                for (int i = 1; i <=2 ; i++) {
                    if (pages.size() < totalPage) {
                        pages.add(currentPage + i);
                    }
                }
                break;
            default:
                for (int i = 1; i <= 3; i++) {
                    if (currentPage - i >= currentPage - 1 && pages.size() <= 2) {
                        pages.add(0, currentPage - i);
                    }
                    if (currentPage + i <= totalPage && pages.size() < 3) {
                        pages.add(currentPage + i);
                    }
                }
                break;

        }

        //是否展示上一页
        if (currentPage == 1) {
            showPrevious = Boolean.FALSE;
            showNext = Boolean.TRUE;
        }
        //是否展示下一页
        if (currentPage.equals(totalPage)) {
            showNext = Boolean.FALSE;
            showPrevious = Boolean.TRUE;
        }
        if (currentPage > 1 && currentPage < totalPage){
            showPrevious = Boolean.TRUE;
            showNext=Boolean.TRUE;
        }

        //是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = Boolean.FALSE;
        } else {
            showFirstPage = Boolean.TRUE;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = Boolean.FALSE;
        } else {
            showEndPage = Boolean.TRUE;
        }
    }
}
