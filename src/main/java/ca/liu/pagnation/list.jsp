<!-- 
<%@ include file="/WEB-INF/jspf/include.jspf" %>
<html>
  <head>
  	<%@ include file="/WEB-INF/jspf/dataTable.jspf" %>
	<%@ include file="/WEB-INF/jspf/datePicker.jspf" %>
    <title>${msg.auditLogTitle}</title>
  </head>
  <body>
    <div id="audit" data-navitem="auditlog" class="content">
      <%@ include file="/WEB-INF/jspf/flashAlert.jspf" %>
      <div class="dataTables_wrapper form-inline">
        <form id="searchForm">
          <div>
            <input type="text" readonly="readonly" value="${startDate}" id="startDate" name="startDate" data-date-format="mm/dd/yyyy" placeholder="MM/DD/YYYY"/>
            <label>to</label>
            <input type="text" readonly="readonly" value="${endDate}" id="endDate" name="endDate" data-date-format="mm/dd/yyyy" placeholder="MM/DD/YYYY"/>
            <input type="text" id="searchText" name="searchText"
                   value="${searchText}" placeholder="${msg.auditLogSearch}"
                   class="input-xlarge search-query" />
            <input type="submit"
                   id="submit" class="btn btn-primary" value="${msg.searchLabel}" />
            <a type="reset" id="resetButton" href="auditlog"
               title="${msg.reset}" class="btn"><i class="icon-remove"></i>
              ${msg.reset}</a>
          </div>
          <br/>
          <div class="row-fluid">
            <div class="span6">
              <div class="dataTables_length">
                <label>
                  <custom:selectOne id="selectedPageSize"
                                    selectedValue="${selectedPageSize}">
                    <custom:options items="${pageSizes}" />
                  </custom:selectOne>
                  records per page </label>
              </div>
            </div>
          </div>
        </form>
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>${msg.auditLogLoggedDate}</th>
              <th>${msg.auditLogDescription}</th>
              <th>${msg.auditLogUsername}</th>
              <th>${msg.auditLogOrganization}</th>
            </tr>
          </thead>
          <tbody id="logListing">
            <c:forEach var="row" items="${auditEvents}" varStatus="rowStatus">
              <tr>
                <td>${row.createdAt}</td>
                <c:choose>
                	<c:when test="${!empty row.entityName}">
                		<td>${row.message} '<a href="${row.entityUri}">${row.entityName}</a>'</td>
                	</c:when>
                	<c:otherwise>
                		<td>${row.message}</td>
                	</c:otherwise>
                </c:choose>
                <td>${row.userAccount.email}</td>
                <td><a class="merchant" href="organization/${row.organization.organizationId}/choose">${row.organization.name}</a></td>
              </tr>
            </c:forEach>
            <c:if test="${empty auditEvents}">
              <tr>
                <td colspan="4">
                  <div class="alert alert-info">${msg.auditLogEmpty}</div>
                </td>
              </tr>
            </c:if>
          </tbody>
        </table>
        <div class="row-fluid">
          <div class="span6">
            <div class="dataTables_info" id="searchResults_info">Showing
              ${startIndex} to ${endIndex} of ${totalCount} entries</div>
          </div>
          <div class="span6">
            <div class="dataTables_paginate paging_bootstrap pagination">
              <ul>
                <c:forEach var="row" items="${paginationMetas}">
                  <li class="${row.styleClass}"><a
                      href="auditlog?page=${row.pageNumber}&searchText=${searchText}&selectedPageSize=${selectedPageSize}&startDate=${startDate}&endDate=${endDate}">${row.text}</a>
                  </li>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <script>
        $('#selectedPageSize').change(function() {
          $('#submit').click();
        });

        $('#startDate')
        .datepicker()
        .on('changeDate', function(ev){
          $('#startDate').datepicker('hide');
          $('#startDate').blur();
        });
        $('#endDate')
        .datepicker()
        .on('changeDate', function(ev){
          $('#endDate').datepicker('hide');
          $('#endDate').blur();
        });
      </script>
    </div>
  </body>
</html>
-->