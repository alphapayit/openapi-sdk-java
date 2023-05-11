import com.alibaba.fastjson.JSONObject
import com.alphapay.api.APIs
import com.alphapay.api.AlphaPayClient
import com.alphapay.api.DefaultAlphaPayClient
import com.alphapay.api.model.ResultStatusType
import com.alphapay.api.request.beans.SearchRefundRequest
import com.alphapay.api.response.beans.SearchRefundResponse
import spock.lang.Specification

class SearchRefundSpecification extends Specification {

    def "search refund"() {
        given:
        def merchantCode = "PFA9QX"
        def gateway = System.getenv("OPEN_API_GATEWAY")
        def merchantKey = System.getenv("TEST_MCH_KEY")
        def platformKey = System.getenv("TEST_PLT_KEY")
        AlphaPayClient client = new DefaultAlphaPayClient(gateway, merchantKey, platformKey)

        when:
        SearchRefundRequest request = new SearchRefundRequest()
        request.setPath(APIs.SEARCH_REFUND)
        request.setMerchantCode(merchantCode)
        request.setRefundId("")
        SearchRefundResponse response = client.execute(request)

        then:
        System.out.println(JSONObject.toJSONString(response))
        response.result != null
        response.result.resultCode == "SUCCESS"
        response.result.resultStatus == ResultStatusType.S
        response.refundId != null
        response.refundRequestId != null
        response.paymentId != null
        response.refund != null
        response.refundStatus == "SUCCESS"
        response.refundTime != null
    }
}
