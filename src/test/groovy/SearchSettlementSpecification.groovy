import com.alibaba.fastjson.JSONObject
import com.alphapay.api.APIs
import com.alphapay.api.AlphaPayClient
import com.alphapay.api.DefaultAlphaPayClient
import com.alphapay.api.model.ResultStatusType
import com.alphapay.api.request.beans.SearchSettlementsRequest
import com.alphapay.api.response.beans.SearchSettlementsResponse
import spock.lang.Specification

class SearchSettlementSpecification extends Specification {

    def "search settlement"() {
        given:
        def merchantCode = "PFA9QX"
        def gateway = System.getenv("OPEN_API_GATEWAY")
        def merchantKey = System.getenv("TEST_MCH_KEY")
        def platformKey = System.getenv("TEST_PLT_KEY")
        AlphaPayClient client = new DefaultAlphaPayClient(gateway, merchantKey, platformKey)

        when:
        SearchSettlementsRequest request = new SearchSettlementsRequest()
        request.setPath(APIs.SEARCH_SETTLEMENTS)
        request.setMerchantCode(merchantCode)
        request.setDate("20211215")
        SearchSettlementsResponse response = client.execute(request)

        then:
        System.out.println(JSONObject.toJSONString(response))
        response.result != null
        response.result.resultCode == "SUCCESS"
        response.result.resultStatus == ResultStatusType.S
    }
}
