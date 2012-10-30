//package ca.liu.pagnation;
//
//import com.admeris.foundation.web.Flash;
//import com.admeris.merchantmanagement.core.audit.OrganizationContext;
//import com.admeris.merchantmanagement.core.entity.audit.AuditEvent;
//import com.admeris.merchantmanagement.core.entity.audit.AuditEventRepository;
//import com.admeris.merchantmanagement.core.entity.organization.Organization;
//import com.admeris.merchantmanagement.core.security.Permission;
//import com.admeris.merchantmanagement.core.security.Resources;
//import com.admeris.security.api.Authorize;
//import com.sun.jersey.api.view.Viewable;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.DefaultValue;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.QueryParam;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Authorize(resource = Resources.AUDIT_LOG)
//@Path(AuditLogController.RESOURCE_PATH)
//@RequestScoped
//public class AuditLogController {
//
//	private static final Logger LOGGER =
//	        LoggerFactory.getLogger(AuditLogController.class);
//
//	protected static final String RESOURCE_PATH = "auditlog";
//	private static final String AUDIT_LOG_VIEW = "/auditLog/list";
//
//	@Inject
//	private OrganizationContext organizationContext;
//
//	@Inject
//	private AuditEventRepository auditEventRepository;
//
//    @Inject
//    private Flash flash;
//
//	public static class Model {
//		private String searchText;
//		private List<AuditEvent> auditEvents;
//		private List<PaginationMeta> paginationMetas;
//
//		private String startDate;
//		private String endDate;
//
//		private int startIndex;
//		private int endIndex;
//		private int totalCount;
//		private int selectedPageSize;
//
//		public Model() {
//			searchText = "";
//			paginationMetas = new ArrayList<PaginationMeta>(9);
//		}
//
//		public String getStartDate() {
//			return startDate;
//		}
//
//		public void setStartDate(String startDate) {
//			this.startDate = startDate;
//		}
//
//		public String getEndDate() {
//			return endDate;
//		}
//
//		public void setEndDate(String endDate) {
//			this.endDate = endDate;
//		}
//
//		public int getSelectedPageSize() {
//			return selectedPageSize;
//		}
//
//		public void setSelectedPageSize(int selectedPageSize) {
//			this.selectedPageSize = selectedPageSize;
//		}
//
//		public List<AuditEvent> getAuditEvents() {
//			return auditEvents;
//		}
//
//		public List<Integer> getPageSizes() {
//			return Arrays.asList(5, 10, 25, 50, 100);
//		}
//
//		public int getEndIndex() {
//			return endIndex;
//		}
//
//		public List<PaginationMeta> getPaginationMetas() {
//			return paginationMetas;
//		}
//
//		public String getSearchText() {
//			return searchText;
//		}
//
//		public int getStartIndex() {
//			return startIndex;
//		}
//
//		public int getTotalCount() {
//			return totalCount;
//		}
//
//		public void setAuditEvents(List<AuditEvent> auditEvents) {
//			this.auditEvents = auditEvents;
//		}
//
//		public void setEndIndex(int endIndex) {
//			this.endIndex = endIndex;
//		}
//
//		public void setPaginationMetas(List<PaginationMeta> paginationMetas) {
//			this.paginationMetas = paginationMetas;
//		}
//
//		public void setSearchText(String searchText) {
//			this.searchText = searchText;
//		}
//
//		public void setStartIndex(int startIndex) {
//			this.startIndex = startIndex;
//		}
//
//		public void setTotalCount(int totalCount) {
//			this.totalCount = totalCount;
//		}
//	}
//
//	public static class PaginationMeta {
//		private int pageNumber;
//		private String text;
//		private String styleClass;
//
//		public PaginationMeta(int pageNumber, String text, String styleClass) {
//			this.setPageNumber(pageNumber);
//			this.setText(text);
//			this.setStyleClass(styleClass);
//		}
//
//		public int getPageNumber() {
//			return pageNumber;
//		}
//
//		public String getStyleClass() {
//			return styleClass;
//		}
//
//		public String getText() {
//			return text;
//		}
//
//		public void setPageNumber(int pageNumber) {
//			this.pageNumber = pageNumber;
//		}
//
//		public void setStyleClass(String styleClass) {
//			this.styleClass = styleClass;
//		}
//
//		public void setText(String text) {
//			this.text = text;
//		}
//	}
//
//	private void buildPaginationMetas(int pageNumber, int maxPageNumber, List<PaginationMeta> metas) {
//		if(pageNumber == 1) {
//			metas.add(new PaginationMeta(1, "First", "disabled"));
//			metas.add(new PaginationMeta(1, "1", "active"));
//		} else {
//			//first page HTML
//			metas.add(new PaginationMeta(1, "First", ""));
//
//			//n-4 page HTML if on last page
//			int prev4PageNumber = pageNumber - 4;
//			if((pageNumber == maxPageNumber) && prev4PageNumber >= 1) {
//				metas.add(new PaginationMeta(prev4PageNumber, String.valueOf(prev4PageNumber), ""));
//			}
//
//			//n-3 page HTML if on last page or last 2 page
//			int prev3PageNumber = pageNumber - 3;
//			if((pageNumber == maxPageNumber || pageNumber + 1 == maxPageNumber) && prev3PageNumber >= 1) {
//				metas.add(new PaginationMeta(prev3PageNumber, String.valueOf(prev3PageNumber), ""));
//			}
//
//			//n-2 page HTML
//			int prev2PageNumber = pageNumber - 2;
//			if(prev2PageNumber >= 1) {
//				metas.add(new PaginationMeta(prev2PageNumber, String.valueOf(prev2PageNumber), ""));
//			}
//
//			//n-1 page HTML
//			int prevPageNumber = pageNumber - 1;
//			metas.add(new PaginationMeta(prevPageNumber, String.valueOf(prevPageNumber), ""));
//
//			//current page HTML
//			metas.add(new PaginationMeta(pageNumber, String.valueOf(pageNumber), "active"));
//		}
//
//		if(pageNumber >= maxPageNumber) {
//			//last page HTML
//			metas.add(new PaginationMeta(pageNumber, "Last", "disabled"));
//		} else {
//			//n+1 page HTML
//			int nextPageNumber = pageNumber + 1;
//			metas.add(new PaginationMeta(nextPageNumber, String.valueOf(nextPageNumber), ""));
//
//			//n+2 page HTML
//			int next2PageNumber = pageNumber + 2;
//			if(next2PageNumber <= maxPageNumber) {
//				metas.add(new PaginationMeta(next2PageNumber, String.valueOf(next2PageNumber), ""));
//			}
//
//			//n+3 page HTML if on first page or second page
//			int next3PageNumber = pageNumber + 3;
//			if((pageNumber == 1 || pageNumber == 2) && next3PageNumber <= maxPageNumber) {
//				metas.add(new PaginationMeta(next3PageNumber, String.valueOf(next3PageNumber), ""));
//			}
//
//			//n+4 page HTML if on first page
//			int next4PageNumber = pageNumber + 4;
//			if((pageNumber == 1) && next4PageNumber <= maxPageNumber) {
//				metas.add(new PaginationMeta(next4PageNumber, String.valueOf(next4PageNumber), ""));
//			}
//
//			//last page HTML
//			metas.add(new PaginationMeta(maxPageNumber, "Last", ""));
//		}
//	}
//
//	private Date parseDate(String string) {
//		return parseDate(string, 0);
//	}
//
//	//get very end of day if with offset
//	private Date parseDate(String string, int dayOffSet) {
//		Date date = null;
//		if(!string.trim().isEmpty()) {
//			try {
//				date = new SimpleDateFormat("MM/dd/yyyy").parse(string);
//				if(dayOffSet != 0) {
//					Calendar cal = Calendar.getInstance();
//					cal.setTime(date);
//					cal.add(Calendar.DAY_OF_MONTH, dayOffSet);
//					date = new Date(cal.getTimeInMillis() - 1);
//				}
//			} catch (ParseException e) {
//				LOGGER.error("Error when parsing date.", e);
//			}
//		}
//		return date;
//	}
//
//	@GET
//	public Viewable show(@DefaultValue("1") @QueryParam("page") int pageNumber,
//					     @DefaultValue("") @QueryParam("searchText") String searchText,
//					     @DefaultValue("10") @QueryParam("selectedPageSize") int selectedPageSize,
//					     @DefaultValue("") @QueryParam("startDate") String startDate,
//					     @DefaultValue("") @QueryParam("endDate") String endDate) {
//		Model model = new Model();
//		model.setSearchText(searchText);
//		model.setStartDate(startDate);
//		model.setEndDate(endDate);
//		model.setSelectedPageSize(selectedPageSize);
//
//		//date range validation
//		Date start = parseDate(startDate);
//		Date end = parseDate(endDate, 1);
//		if(start != null && end != null && start.after(end)) {
//			flash.error("dateRange.error");
//			return new Viewable(AUDIT_LOG_VIEW, model);
//		}
//
//		//if user is admeris admin, show all the organization logs
//		Organization org = organizationContext.getOrganization();
//		if (Permission.ADMIN_IMPLICIT_ORGANIZATION_MEMBERSHIP.isPermitted()) {
//			org = null;
//		}
//
//		model.setAuditEvents(auditEventRepository.search(model.getSearchText(), org, start, end, pageNumber, model.getSelectedPageSize()));
//		model.setTotalCount((int)auditEventRepository.searchCount(model.getSearchText(), org, start, end));
//		model.setStartIndex((pageNumber - 1) * model.getSelectedPageSize() + 1);
//		if(model.getTotalCount() == 0) {
//			model.setStartIndex(0);
//		}
//		model.setEndIndex(model.getStartIndex() + model.getSelectedPageSize() - 1);
//		if(model.getEndIndex() > model.getTotalCount()) {
//			model.setEndIndex(model.getTotalCount());
//		}
//
//		int maxPageNumber = (int) Math.ceil((double)model.getTotalCount()/(double)model.getSelectedPageSize());
//		buildPaginationMetas(pageNumber, maxPageNumber, model.getPaginationMetas());
//
//		return new Viewable(AUDIT_LOG_VIEW, model);
//	}
//}
