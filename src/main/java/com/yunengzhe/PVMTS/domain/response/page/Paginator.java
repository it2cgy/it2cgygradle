package com.yunengzhe.PVMTS.domain.response.page;

 
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * 分页包装类
 *
 * @author <a href="mailto:ergal@163.com">vincent.omg</a>
 * @version 1.0
 * @date 16/6/6
 * @since 1.0
 */
public class Paginator {

	public static int DEFAULT_PAGESIZE = 20; 
	public static int ALARM_MAX_PAGESIZE = 500; 
    private static String PAGE_SIZE_NAME = "pagesize"; 
    private static String PAGE_NAME = "page";
    private static int DEFAULT_MAX_PAGESIZE = 200;
    
    // 请求
    private HttpServletRequest request;

    //分页参数配置器 

    // 总数
    private Long total;

    // 每页大小
    private Integer pageSize;

    // 当前页
    private Integer currentPage;

    // 下一页
    private Integer nextPage;

    // 上一页
    private Integer previousPage;

    // 最后页
    private Integer lastPage;

    // 第一页
    private Integer firstPage;

    // 总页数
    private Integer totalPage;

    // 查询url 不包含页码
    private String queryUrl;

    // 当前页url
    private String currentUrl;

    // 上一页url
    private String previousUrl;

    // 下一页url
    private String nextUrl;
    
    //第一页
    private String firstUrl;
    //最后一页
    private String lastUrl;
    // 主机名 不带http
    private String host;

    // 端口
    private String port;

    // scheme
    private String scheme;


    /**
     * 初始化,设置好 currentPage, pageSize, currentUrl
     *
     * @param request
     * @throws URISyntaxException 
     * @throws MalformedURLException 
     */
    public Paginator(HttpServletRequest request,long totalCount) throws MalformedURLException, URISyntaxException {
        this.request = request; 
        this.total = totalCount;
        // page和pageSize参数拿出来
        this.initPage(request);
        this.initPageSize(request);
        String requestUrl = request.getRequestURL().toString();
        String queryUri = request.getQueryString();
        if(StringUtils.isEmpty(queryUri)){
        	queryUri = PAGE_SIZE_NAME+"="+this.currentPage;
        }
        
        if(queryUri.indexOf(PAGE_SIZE_NAME)<0){
        	queryUri = queryUri+"&" +PAGE_SIZE_NAME+"="+this.currentPage;
        }
        this.setCurrentUrl(requestUrl + "?" + queryUri);
        this.build();
        // ServletUriComponentsBuilder ucb = ServletUriComponentsBuilder.fromRequest(request);

    }

    public void initPage(HttpServletRequest request) {
        String pageStr = request.getParameter(PAGE_NAME);
        if (pageStr != null) {
            this.currentPage = Integer.parseInt(pageStr);
        } else {
            this.currentPage = 1;
        }

    }

    public void initPageSize(HttpServletRequest request) {
        String pageSizeStr = request.getParameter(PAGE_SIZE_NAME);
        if (pageSizeStr != null) {
            int pageSize = Integer.parseInt(pageSizeStr);
            this.pageSize = pageSize > DEFAULT_MAX_PAGESIZE ? DEFAULT_MAX_PAGESIZE : pageSize;
        } else {
            this.pageSize = DEFAULT_PAGESIZE;
        }

    }


    /**
     * 如果设置了totalCount,直接build next
     */
    public void build() throws  URISyntaxException, MalformedURLException {
    
        // 计算总页数
        this.setTotalPage((int) (this.total / this.pageSize + (this.total % this.pageSize == 0 ? 0 : 1)));
        // 设置第一页
        this.setFirstPage(1);


        // 计算下一页 上一页
        Integer nextPageNum = this.currentPage.intValue()>=this.getTotalPage().intValue() ? null : this.currentPage + 1;
        if (this.getTotalPage() == 0) {
            nextPageNum = null;
        }

        Integer previousPageNum = this.currentPage.equals(new Integer(1)) ? null : this.currentPage - 1;
        if(previousPageNum!=null && previousPageNum.intValue()>=this.getTotalPage()){
        	previousPageNum = this.getTotalPage();
        }
        String pageValue = this.request.getParameter(PAGE_NAME);
        this.buildNextPage(nextPageNum, pageValue);
        this.buildPreviousPage(previousPageNum, pageValue);
        this.buildFirstPage(pageValue);
        this.buildLastPage(pageValue);
    }

    
    private void buildNextPage(Integer nextPageNum, String pageValue) throws URISyntaxException, MalformedURLException {
        // 构建一个url
        URIBuilder urlb = new URIBuilder(this.getCurrentUrl());
        if (nextPageNum != null) {
            // 设置下一页
            if (pageValue != null) {
                urlb.setParameter(PAGE_NAME, String.valueOf(nextPageNum));
            } else {
                urlb.addParameter(PAGE_NAME, String.valueOf(nextPageNum));
            }
            buildUrlHost(urlb);

            this.setNextPage(nextPageNum);
            this.setNextUrl(urlb.build().toURL().toString());
        } else {
            this.setNextUrl("");
        }
    }

    private void buildFirstPage(String pageValue) throws URISyntaxException, MalformedURLException {
        // 构建一个url
        URIBuilder urlb = new URIBuilder(this.getCurrentUrl());
        urlb.setParameter(PAGE_NAME, String.valueOf(1));
        this.setFirstUrl(urlb.build().toURL().toString());
    }
    
    private void buildLastPage(String pageValue) throws URISyntaxException, MalformedURLException {
        // 构建一个url 
        URIBuilder urlb = new URIBuilder(this.getCurrentUrl());
        urlb.setParameter(PAGE_NAME, String.valueOf(this.totalPage));
        this.setLastUrl(urlb.build().toURL().toString());
    }
    private void buildPreviousPage(Integer previousPageNum, String pageValue) throws URISyntaxException, MalformedURLException {
        // 构建一个url
        URIBuilder urlb = new URIBuilder(this.getCurrentUrl());
        if (previousPageNum != null) {
            // 设置下一页
            if (pageValue != null) {
                urlb.setParameter(PAGE_NAME, String.valueOf(previousPageNum));
            } else {
                urlb.addParameter(PAGE_NAME, String.valueOf(previousPageNum));
            }
            buildUrlHost(urlb);
            this.setPreviousPage(previousPageNum);
            this.setPreviousUrl(urlb.build().toURL().toString());
        } else {
            this.setPreviousUrl("");
        }
    }

    private void buildUrlHost(URIBuilder urlb) {
        if (this.host != null) {
            urlb.setHost(this.host);
        }
        if (this.port != null) {
            urlb.setPort(Integer.parseInt(this.port));
        }
        if (this.scheme != null) {
            urlb.setScheme(this.scheme);
        }
    }
 

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public void setPreviousUrl(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }
 
    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

	public String getFirstUrl() {
		return firstUrl;
	}

	public void setFirstUrl(String firstUrl) {
		this.firstUrl = firstUrl;
	}

	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}
    
    
}
