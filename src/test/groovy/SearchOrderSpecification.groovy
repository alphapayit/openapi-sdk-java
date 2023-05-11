import com.alibaba.fastjson.JSONObject
import com.alphapay.api.APIs
import com.alphapay.api.AlphaPayClient
import com.alphapay.api.DefaultAlphaPayClient
import com.alphapay.api.model.ResultStatusType
import com.alphapay.api.request.beans.SearchOrderRequest
import com.alphapay.api.response.beans.SearchOrderResponse
import spock.lang.Specification

class SearchOrderSpecification extends Specification {

    def "search order"() {
        given:
        def merchantCode = "PFA9QX"
        def gateway = System.getenv("OPEN_API_GATEWAY")
        def merchantKey = System.getenv("TEST_MCH_KEY")
        def platformKey = System.getenv("TEST_PLT_KEY")
        AlphaPayClient client = new DefaultAlphaPayClient(gateway, merchantKey, platformKey)

        when:
        SearchOrderRequest request = new SearchOrderRequest()
        request.setPath(APIs.SEARCH_ORDER)
        request.setMerchantCode(merchantCode)
        request.setPaymentId("2305111547584020147632479")
        SearchOrderResponse response = client.execute(request)

        then:
        System.out.println(JSONObject.toJSONString(response))
        response.result != null
        response.result.resultCode == "SUCCESS"
        response.result.resultStatus == ResultStatusType.S
        response.scenarioCode != null
        response.paymentId != null
        response.paymentRequestId != null
        response.order != null
        response.order.orderAmount != null
        response.order.orderAmount.value != null
        response.order.orderAmount.currency != null
        response.paymentStatus != null
    }
}
