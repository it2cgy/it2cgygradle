package com.yunengzhe.PVMTS.domain.response;

import java.util.List;
 
public class ListResult<T>{
    private long counts;
    private long pageCounts;
    private long currentPage;
    private long pagesize;

    private String first; 
	private String next; 
    private String privious;
    private String last; 
    
    private List<T> results;
 
    public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public long getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(long pageCounts) {
		this.pageCounts = pageCounts;
	}

	 

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getPagesize() {
		return pagesize;
	}

	public void setPagesize(long pagesize) {
		this.pagesize = pagesize;
	}

    public long getCounts() {
        return counts;
    }

    public void setCounts(long counts) {
        this.counts = counts;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrivious() {
        return privious;
    }

    public void setPrivious(String privious) {
        this.privious = privious;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}