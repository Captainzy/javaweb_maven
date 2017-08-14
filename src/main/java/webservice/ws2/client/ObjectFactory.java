
package webservice.ws2.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice.ws2.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetOrderMapResponse_QNAME = new QName("http://server.ws2.webservice/", "getOrderMapResponse");
    private final static QName _GetOrderListResponse_QNAME = new QName("http://server.ws2.webservice/", "getOrderListResponse");
    private final static QName _GetOrderMap_QNAME = new QName("http://server.ws2.webservice/", "getOrderMap");
    private final static QName _ProcessOrder_QNAME = new QName("http://server.ws2.webservice/", "processOrder");
    private final static QName _Order_QNAME = new QName("http://server.ws2.webservice/", "Order");
    private final static QName _ProcessOrderResponse_QNAME = new QName("http://server.ws2.webservice/", "processOrderResponse");
    private final static QName _GetOrderList_QNAME = new QName("http://server.ws2.webservice/", "getOrderList");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice.ws2.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOrderMapResponse }
     * 
     */
    public GetOrderMapResponse createGetOrderMapResponse() {
        return new GetOrderMapResponse();
    }

    /**
     * Create an instance of {@link GetOrderMapResponse.Return }
     * 
     */
    public GetOrderMapResponse.Return createGetOrderMapResponseReturn() {
        return new GetOrderMapResponse.Return();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link ProcessOrder }
     * 
     */
    public ProcessOrder createProcessOrder() {
        return new ProcessOrder();
    }

    /**
     * Create an instance of {@link ProcessOrderResponse }
     * 
     */
    public ProcessOrderResponse createProcessOrderResponse() {
        return new ProcessOrderResponse();
    }

    /**
     * Create an instance of {@link GetOrderList }
     * 
     */
    public GetOrderList createGetOrderList() {
        return new GetOrderList();
    }

    /**
     * Create an instance of {@link GetOrderListResponse }
     * 
     */
    public GetOrderListResponse createGetOrderListResponse() {
        return new GetOrderListResponse();
    }

    /**
     * Create an instance of {@link GetOrderMap }
     * 
     */
    public GetOrderMap createGetOrderMap() {
        return new GetOrderMap();
    }

    /**
     * Create an instance of {@link GetOrderMapResponse.Return.Entry }
     * 
     */
    public GetOrderMapResponse.Return.Entry createGetOrderMapResponseReturnEntry() {
        return new GetOrderMapResponse.Return.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderMapResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.ws2.webservice/", name = "getOrderMapResponse")
    public JAXBElement<GetOrderMapResponse> createGetOrderMapResponse(GetOrderMapResponse value) {
        return new JAXBElement<GetOrderMapResponse>(_GetOrderMapResponse_QNAME, GetOrderMapResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.ws2.webservice/", name = "getOrderListResponse")
    public JAXBElement<GetOrderListResponse> createGetOrderListResponse(GetOrderListResponse value) {
        return new JAXBElement<GetOrderListResponse>(_GetOrderListResponse_QNAME, GetOrderListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderMap }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.ws2.webservice/", name = "getOrderMap")
    public JAXBElement<GetOrderMap> createGetOrderMap(GetOrderMap value) {
        return new JAXBElement<GetOrderMap>(_GetOrderMap_QNAME, GetOrderMap.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.ws2.webservice/", name = "processOrder")
    public JAXBElement<ProcessOrder> createProcessOrder(ProcessOrder value) {
        return new JAXBElement<ProcessOrder>(_ProcessOrder_QNAME, ProcessOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Order }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.ws2.webservice/", name = "Order")
    public JAXBElement<Order> createOrder(Order value) {
        return new JAXBElement<Order>(_Order_QNAME, Order.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.ws2.webservice/", name = "processOrderResponse")
    public JAXBElement<ProcessOrderResponse> createProcessOrderResponse(ProcessOrderResponse value) {
        return new JAXBElement<ProcessOrderResponse>(_ProcessOrderResponse_QNAME, ProcessOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrderList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.ws2.webservice/", name = "getOrderList")
    public JAXBElement<GetOrderList> createGetOrderList(GetOrderList value) {
        return new JAXBElement<GetOrderList>(_GetOrderList_QNAME, GetOrderList.class, null, value);
    }

}
