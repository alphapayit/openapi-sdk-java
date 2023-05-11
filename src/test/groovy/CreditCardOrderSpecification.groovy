import com.alphapay.api.APIs
import com.alphapay.api.AlphaPayClient
import com.alphapay.api.DefaultAlphaPayClient
import com.alphapay.api.model.ResultStatusType
import com.alphapay.api.model.beans.Amount
import com.alphapay.api.model.beans.BankCard
import com.alphapay.api.model.beans.Order
import com.alphapay.api.model.beans.PaymentCard
import com.alphapay.api.model.beans.Refund
import com.alphapay.api.request.beans.CreateOrderRequest
import com.alphapay.api.request.beans.CreateRefundRequest
import com.alphapay.api.response.beans.CreateOrderResponse
import com.alphapay.api.response.beans.CreateRefundResponse
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.time.DateFormatUtils
import spock.lang.Specification

class CreditCardOrderSpecification extends Specification {

    def "create credit card order"() {
        given:
        def merchantCode = "PFA9QX"
        def gateway = System.getenv("OPEN_API_GATEWAY")
        def merchantKey = System.getenv("TEST_MCH_KEY")
        def platformKey = System.getenv("TEST_PLT_KEY")
        AlphaPayClient client = new DefaultAlphaPayClient(gateway, merchantKey, platformKey)

        def cardNo = System.getenv("TEST_CARD_NO")
        def cardExpM = System.getenv("TEST_CARD_EXP_M")
        def cardExpY = System.getenv("TEST_CARD_EXP_Y")
        def cardCvv = System.getenv("TEST_CARD_CVV")
        def cardHolder = System.getenv("TEST_CARD_HOLDER")
        def cardAddr = System.getenv("TEST_CARD_ADDR")
        def cardCity = System.getenv("TEST_CARD_CITY")
        def cardState = System.getenv("TEST_CARD_STATE")
        def cardCountry = System.getenv("TEST_CARD_COUNTRY")
        def cardZipCode = System.getenv("TEST_CARD_ZIP_CODE")

        when:
        def mchOrderId = 'PTM000' + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomStringUtils.random(3, true, false).toUpperCase()
        CreateOrderRequest request = new CreateOrderRequest()
        request.setPath(APIs.PAY)
        request.setMerchantCode(merchantCode)
        request.setScenarioCode(scenarioCode)
        request.setPaymentRequestId(mchOrderId)
        Order order = new Order()
        order.setDescription("小浣熊干脆面 x3")
        Amount orderAmount = new Amount()
        orderAmount.setValue(amount)
        orderAmount.setCurrency(currency)
        order.setOrderAmount(orderAmount)
        request.setOrder(order)
        PaymentCard card = new PaymentCard()
        BankCard bankCard = new BankCard()
        bankCard.setNumber(cardNo)
        bankCard.setExpMonth(cardExpM as Integer)
        bankCard.setExpYear(cardExpY as Integer)
        bankCard.setCvv(cardCvv)
        bankCard.setHolder(cardHolder)
        bankCard.setAddress(cardAddr)
        bankCard.setCity(cardCity)
        bankCard.setState(cardState)
        bankCard.setCountry(cardCountry)
        bankCard.setPostalCode(cardZipCode)
        card.setInfo(bankCard)
        card.setRemember(remember)
        card.setCustomerId(customerId)
        card.setEmail(email)
        request.setCard(card)
        CreateOrderResponse response = client.execute(request)

        then:
        response.result != null
        response.result.resultCode == resultCode
        response.result.resultStatus != ResultStatusType.F
        response.scenarioCode == scenarioCode
        response.paymentId != null
        response.paymentRequestId != null
        response.paymentCreateTime != null
        response.paymentExpireTime != null
        response.order != null
        response.card != null
        response.card.cardId != null

        def refundRequestId = 'PRT000' + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + RandomStringUtils.random(3, true, false).toUpperCase()
        CreateRefundRequest refundRequest = new CreateRefundRequest()
        refundRequest.setPath(APIs.REFUND)
        refundRequest.setMerchantCode(merchantCode)
        refundRequest.setPaymentId(response.paymentId)
        refundRequest.setRefundRequestId(refundRequestId)
        Refund refund = new Refund()
        Amount refundAmount = new Amount()
        refundAmount.setValue(amount)
        refundAmount.setCurrency(currency)
        refund.setRefundAmount(refundAmount)
        refund.setDescription("PROD_REFUND")
        refundRequest.setRefund(refund)
        CreateRefundResponse refundResponse = client.execute(refundRequest)
        refundResponse.result != null
        refundResponse.result.resultCode == "SUCCESS"
        refundResponse.result.resultStatus == ResultStatusType.S

        where:
        scenarioCode  | amount | currency | remember | customerId | email         || resultCode
        "CREDIT_CARD" | "100"  | "CNY"    | null     | null       | null          || "PAYMENT_IN_PROCESS"
        "CREDIT_CARD" | "100"  | "CAD"    | null     | null       | null          || "PAYMENT_IN_PROCESS"
        "CREDIT_CARD" | "100"  | "CAD"    | false    | null       | null          || "PAYMENT_IN_PROCESS"
        "CREDIT_CARD" | "100"  | "CAD"    | true     | "C001"     | "abc@abc.com" || "PAYMENT_IN_PROCESS"
    }
}
