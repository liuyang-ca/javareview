package ca.liu.ws.soap;

import javax.jws.WebService;
import java.util.List;
import ca.liu.domain.Log;
import javax.jws.WebParam;
import javax.jws.WebMethod;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.jws.soap.SOAPBinding;
import javax.jws.WebResult;

@WebService(targetNamespace = "javareview/ca/liu/spring/service", name = "LogServicePort")
public interface LogServiceEndPoint	{

	@SOAPBinding()
	@ResponseWrapper(localName = "deleteResponse", className = "deleteResponse")
	@RequestWrapper(localName = "delete", className = "delete")
	@WebMethod(operationName = "delete")
	void delete(@WebParam(partName = "logPart", name = "log") Log log);

	@SOAPBinding()
	@WebResult(partName = "getOutputPart", name = "getOutput")
	@ResponseWrapper(localName = "getResponse", className = "getResponse")
	@RequestWrapper(localName = "get", className = "get")
	@WebMethod(operationName = "get")
	Log get(@WebParam(partName = "idPart", name = "id") int id);

	@SOAPBinding()
	@WebResult(partName = "listOutputPart", name = "listOutput")
	@ResponseWrapper(localName = "listResponse", className = "listResponse")
	@RequestWrapper(localName = "list", className = "list")
	@WebMethod(operationName = "list")
	List<Log> list();

	@SOAPBinding()
	@ResponseWrapper(localName = "saveResponse", className = "saveResponse")
	@RequestWrapper(localName = "save", className = "save")
	@WebMethod(operationName = "save")
	void save(@WebParam(partName = "logPart", name = "log") Log log);

	@SOAPBinding()
	@ResponseWrapper(localName = "updateResponse", className = "updateResponse")
	@RequestWrapper(localName = "update", className = "update")
	@WebMethod(operationName = "update")
	void update(@WebParam(partName = "logPart", name = "log") Log log);

}