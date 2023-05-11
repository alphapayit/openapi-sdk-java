import com.alibaba.fastjson.JSONObject
import com.alphapay.api.APIs
import com.alphapay.api.AlphaPayClient
import com.alphapay.api.DefaultAlphaPayClient
import com.alphapay.api.model.ResultStatusType
import com.alphapay.api.model.beans.TransactionType
import com.alphapay.api.request.beans.SearchTransactionsRequest
import com.alphapay.api.response.beans.SearchTransactionsResponse
import spock.lang.Specification

class SearchTxSpecification extends Specification {

    def "search tx"() {
        given:
        def merchantCode = "PFA9QX"
        def gateway = System.getenv("OPEN_API_GATEWAY")
        def merchantKey = System.getenv("TEST_MCH_KEY")
        def platformKey = System.getenv("TEST_PLT_KEY")
        AlphaPayClient client = new DefaultAlphaPayClient(gateway, merchantKey, platformKey)

        when:
        SearchTransactionsRequest request = new SearchTransactionsRequest()
        request.setPath(APIs.SEARCH_TRANSACTIONS)
        request.setMerchantCode(merchantCode)
        request.setDate("20211215")
        request.setTransactionType(TransactionType.All)
        request.setPage(1)
        request.setPageLimit(10)
        SearchTransactionsResponse response = client.execute(request)

        then:
        System.out.println(JSONObject.toJSONString(response))
        response.result != null
        response.result.resultCode == "SUCCESS"
        response.result.resultStatus == ResultStatusType.S
        response.pagination != null
    }
}
